package com.jft.market.service;

import static com.jft.market.model.QCustomer.customer;
import static com.jft.market.model.QOrderCart.orderCart;
import static com.jft.market.model.QPaymentInstrument.paymentInstrument;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.OrderCartStatus;
import com.jft.market.api.OrderStatus;
import com.jft.market.api.PaymnetInstrumentStatus;
import com.jft.market.api.ws.PaymentResponseWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Customer;
import com.jft.market.model.OrderCart;
import com.jft.market.model.PaymentInstrument;
import com.jft.market.model.PurchaseOrder;
import com.jft.market.repository.CustomerRepository;
import com.jft.market.repository.OrderCartRepository;
import com.jft.market.repository.PurchaseOrderRepository;
import com.jft.market.util.EntityPredicates;
import com.jft.market.util.Preconditions;
import com.litle.sdk.generate.SaleResponse;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;


@Slf4j
@Service("purchaseService")
public class PurchaseServiceimpl implements PurchaseService {

	@Autowired
	private CustomerRepository customerRepository;


	@Autowired
	private VantivServiceImpl vantivService;

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private OrderCartRepository orderCartRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public final String successResponse = "000";
	private SaleResponse saleResponse = null;

	@Override
	@Transactional
	public PaymentResponseWS purchaseProduct(String customerUuid, String orderCartUuid) {
		Customer customer = getCustomer(customerUuid);
		OrderCart orderCart = getOrderCart(orderCartUuid);
		Preconditions.check(customer == null, ExceptionConstants.CUSTOMER_NOT_FOUND);
		Preconditions.check(orderCart == null, ExceptionConstants.NO_ORDER_CART_FOUND);
		List<PaymentInstrument> activePaymentInstruments = getActivePaymentInstruments(customer);
		Preconditions.check(activePaymentInstruments == null, ExceptionConstants.NO_ACTIVE_PAYMNET_INSTRUMENT_FOUND);
		activePaymentInstruments.forEach(paymentInstrument1 -> {
			try {
				saleResponse = vantivService.createSale(paymentInstrument1, orderCart);
				if (saleResponse.getResponse().equals(successResponse)) {
					associateOrderCartAndCustomerWithPurchaseOrder(orderCart, customer);
				} else {
					Preconditions.checkPaymentResponse(!saleResponse.getResponse().equals(successResponse), ExceptionConstants.PAYMENT_ERROR);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return buildPaymentResponse(saleResponse);
	}

	@Override
	@Transactional
	public Customer getCustomer(String customerUuid) {
		JPQLQuery query = new JPAQuery(entityManager);
		Predicate customerPredicate = customer.uuid.eq(customerUuid)
				.and(EntityPredicates.isTimestampedFieldEnabledAndNotDeleted(customer._super));
		return query.from(customer).where(customerPredicate).uniqueResult(customer);
	}

	@Override
	@Transactional
	public OrderCart getOrderCart(String orderCartUuid) {
		JPQLQuery query = new JPAQuery(entityManager);
		Predicate orderCartPredicate = orderCart.uuid.eq(orderCartUuid)
				.and(EntityPredicates.isTimestampedFieldEnabledAndNotDeleted(orderCart._super));
		return query.from(orderCart).where(orderCartPredicate).uniqueResult(orderCart);
	}

	@Override
	@Transactional
	public void associateOrderCartAndCustomerWithPurchaseOrder(OrderCart orderCart, Customer customer) {
		createPurchaseOrder(orderCart, customer);
	}

	@Override
	@Transactional
	public List<PaymentInstrument> getActivePaymentInstruments(Customer customer) {
		JPQLQuery query = new JPAQuery(entityManager);
		Predicate activePaymnetInstruments = paymentInstrument.customer.eq(customer)
				.and(paymentInstrument.status.eq(PaymnetInstrumentStatus.ACTIVE))
				.and(EntityPredicates.isTimestampedFieldEnabledAndNotDeleted(paymentInstrument._super));
		return query.from(paymentInstrument).where(activePaymnetInstruments).list(paymentInstrument);
	}

	@Override
	public PaymentResponseWS buildPaymentResponse(SaleResponse saleResponse) {
		Preconditions.check(saleResponse == null, ExceptionConstants.PATMENT_GATEWAY_ERROR);
		PaymentResponseWS paymentResponseWS = new PaymentResponseWS();
		paymentResponseWS.setStatus(HttpStatus.OK);
		paymentResponseWS.setMessage(saleResponse.getMessage());
		return paymentResponseWS;
	}

/*
	@Override
	@Transactional
	public List<PurchaseOrder> getOrdersByProductId(Product product) {
		JPQLQuery query = new JPAQuery(entityManager);
		Predicate predicate = purchaseOrder.product.eq(product).
				and(EntityPredicates.isTimestampedFieldEnabledAndNotDeleted(purchaseOrder._super));
		return query.from(purchaseOrder).where(predicate).list(purchaseOrder);
	}
*/

	@Override
	@Transactional
	public void createPurchaseOrder(OrderCart orderCart, Customer customer) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		//Associate Order cart with purchase order
		purchaseOrder.setOrderCart(orderCart);
		orderCart.setPurchaseOrder(purchaseOrder);
		orderCart.setStatus(OrderCartStatus.FINISHED);
		// Associte customer with purchase order
		customer.getPurchaseOrders().add(purchaseOrder);
		purchaseOrder.setCustomer(customer);
		purchaseOrder.setOrderStatus(OrderStatus.ACTIVE);
		if (StringUtils.isEmpty(purchaseOrder.getUuid())) {
			purchaseOrder.setUuid(UUID.randomUUID().toString());
		}
		orderCartRepository.save(orderCart);
		customerRepository.save(customer);
		purchaseOrderRepository.save(purchaseOrder);

	}
}

