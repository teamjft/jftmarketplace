package com.jft.market.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import com.jft.market.repository.ProductRepository;
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

	@Autowired
	private ProductRepository productRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public OrderCartWS createEmptyOrderCartForCustomer(String customerUuid) {
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		Optional<OrderCart> initializedOrderCart = checkIfCustomerAlreadyHasInitializedOrderCart(customer);
		Preconditions.check(initializedOrderCart.isPresent(), ExceptionConstants.CUSTOMER_ALREADY_HAS_ORDER_CART);
		OrderCart orderCart = createAndAssociateOrderCart(customer);
		return convertEntityToWS(orderCart);
	}

	@Override
	@Transactional
	public Optional<OrderCart> checkIfCustomerAlreadyHasInitializedOrderCart(Customer customer1) {
		return customer1.getOrderCarts().stream().filter(orderCart1 -> orderCart1.getStatus().equals(OrderCartStatus.INITIALIZED)).findFirst();
	}

	@Override
	@Transactional
	public Set<OrderCart> findCustomerOrderCarts(String customerUuid) {
		return null;
	}

	@Override
	@Transactional
	public OrderCart createAndAssociateOrderCart(Customer customer) {
		OrderCart orderCart = new OrderCart();
		orderCart.setStatus(OrderCartStatus.INITIALIZED);
		orderCart.setUuid(UUID.randomUUID().toString());
		//Association
		orderCart.setCustomer(customer);
		customer.getOrderCarts().add(orderCart);

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
		log.info("Check if customer has any INITIALIZED order cart");
		Optional<OrderCart> initializedOrderCart = checkIfCustomerAlreadyHasInitializedOrderCart(customer);
		if (!initializedOrderCart.isPresent()) {
			log.info("No existing cart found. Creating new order cart");
			OrderCartWS orderCartWS = createEmptyOrderCartForCustomer(customerUuid);
			log.info("Associating product with order cart");
			String orderCartUuid = associateProductWithOrderCart(orderCartWS, productUuid);
			return orderCartUuid;
		} else {
			log.info("Finding product");
			Product product = productService.readProductByUuid(productUuid);
			Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND);
			log.info("checking if product already into this cart");
			Set<Product> products = initializedOrderCart.get().getProducts();
			if (!products.isEmpty()) {
				products.forEach(product1 -> {
					Preconditions.check(product1.getId() == product.getId(), ExceptionConstants.PRODUCT_ALREADY_ADDED_INTO_CART);
				});
			}
			log.info("Product not into cart. Adding this product into cart");
			initializedOrderCart.get().getProducts().add(product);
			product.getOrderCarts().add(initializedOrderCart.get());
			productService.saveProduct(product);
			orderCartRepository.save(initializedOrderCart.get());
			return initializedOrderCart.get().getUuid();
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

	@Override
	@Transactional
	public void removeProductFromOrderCart(String orderCartUuid, String productUuid) {
		OrderCart orderCart = orderCartRepository.findByUuid(orderCartUuid);
		Preconditions.check(orderCart == null, ExceptionConstants.NO_ORDER_CART_FOUND);
		Product product = productRepository.findByUuid(productUuid);
		Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND);
		Customer customer = orderCart.getCustomer();
		Preconditions.check(customer == null, ExceptionConstants.CUSTOMER_NOT_ASSOCIATED_WITH_ORDER_CART);
		if (OrderCartStatus.ACTIVE_ORDER_CARTS.contains(orderCart.getStatus())) {
			Set<Product> products = orderCart.getProducts();
			log.info("Reading order cart products");
			if (!products.isEmpty()) {
				orderCart.getProducts().forEach(product1 -> {
					if (product1.getUuid().equals(product.getUuid())) {
						log.info("Product present in order cart. Deleting association");
						orderCart.getProducts().remove(product);
						product.getOrderCarts().remove(orderCart);
						orderCartRepository.save(orderCart);
						productRepository.save(product);
					} else {
						log.info("product not present in order cart. Nothing to delete");
						Preconditions.check(product1.getUuid().equals(product.getUuid()), ExceptionConstants.PRODUCT_NOT_ASSOCIATED_TO_CART);
					}
				});
			} else {
				Preconditions.check(products.isEmpty(), ExceptionConstants.ORDER_CART_IS_EMPTY);
			}
		}
	}
}
