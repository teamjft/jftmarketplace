package com.jft.market.service;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.OrderCartStatus;
import com.jft.market.api.ws.OrderCartWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Customer;
import com.jft.market.model.OrderCart;
import com.jft.market.model.Product;
import com.jft.market.repository.OrderCartRepository;
import com.jft.market.util.Preconditions;

@Slf4j
@Service("orderCartService")
public class OrderCartServiceImpl implements OrderCartService {

	@Autowired
	private OrderCartRepository orderCartRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Override
	@Transactional
	public OrderCartWS createEmptyOrderCart(String customerUuid) {
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		Boolean isCustomerHasOrderCart = checkIfCustomerAlreadyHasCart(customer);
		Preconditions.check(isCustomerHasOrderCart, ExceptionConstants.CUSTOMER_ALREADY_HAS_ORDER_CART);
		OrderCart orderCart = createAndAssociateOrderCart(customer);
		return convertEntityToWS(orderCart);
	}

	@Override
	@Transactional
	public Boolean checkIfCustomerAlreadyHasCart(Customer customer) {
		return customer.getOrderCart() == null ? false : true;
	}

	@Override
	@Transactional
	public OrderCart createAndAssociateOrderCart(Customer customer) {
		OrderCart orderCart = new OrderCart();
		orderCart.setStatus(OrderCartStatus.INITIALIZED);
		orderCart.setUuid(UUID.randomUUID().toString());
		orderCart.setCustomer(customer);
		customer.setOrderCart(orderCart);

		orderCartRepository.save(orderCart);
		return orderCart;
	}

	@Override
	public OrderCartWS convertEntityToWS(OrderCart orderCart) {
		Preconditions.check(orderCart == null, ExceptionConstants.NO_ORDER_CART_FOUND);
		OrderCartWS orderCartWS = new OrderCartWS();
		orderCartWS.setStatus(orderCart.getStatus());
		orderCartWS.setUuid(orderCart.getUuid());
		return orderCartWS;
	}

	@Override
	@Transactional
	public String createOrderCart(String customerUuid, String productUuid) {
		log.info("Reading customer");
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		log.info("Check if customer already has order cart");
		boolean isCustomerHasOrderCart = checkIfCustomerAlreadyHasCart(customer);
		if (!isCustomerHasOrderCart) {
			log.info("No existing cart found. Creating new order cart");
			OrderCartWS orderCartWS = createEmptyOrderCart(customerUuid);
			log.info("Associating product with order cart");
			String orderCartUuid = associateProductWithOrderCart(orderCartWS, productUuid);
			return orderCartUuid;
		} else {
			log.info("Order cart exist for the customer. Finding order cart");
			OrderCart orderCart = orderCartRepository.findByCustomerUuid(customerUuid);
			log.info("Finding product");
			Product product = productService.readProductByUuid(productUuid);
			Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND);
			log.info("checking if product already into this cart");
			product.getOrderCarts().forEach(orderCart1 -> {
				Preconditions.check(orderCart1.getId() == orderCart.getId(), ExceptionConstants.PRODUCT_ALREADY_ADDED_INTO_CART);
			});
			log.info("Product not into cart. Adding this product into cart");
			orderCart.getProducts().add(product);
			product.getOrderCarts().add(orderCart);

			productService.saveProduct(product);
			orderCartRepository.save(orderCart);
			return orderCart.getUuid();
		}

	}

	@Override
	@Transactional
	public String associateProductWithOrderCart(OrderCartWS orderCartWS, String productUuid) {
		Preconditions.check(orderCartWS == null, ExceptionConstants.NO_ORDER_CART_FOUND);
		log.info("Reading order cart");
		OrderCart orderCart = orderCartRepository.findByUuid(orderCartWS.getUuid());
		Product product = productService.readProductByUuid(productUuid);
		Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND);
		orderCart.getProducts().add(product);
		product.getOrderCarts().add(orderCart);
		log.info("Association done. Saving entities");
		productService.saveProduct(product);
		orderCartRepository.save(orderCart);

		return orderCart.getUuid();
	}
}
