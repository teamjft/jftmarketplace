package com.jft.market.service;


import java.util.List;

import com.jft.market.api.ws.PaymentResponseWS;
import com.jft.market.model.Customer;
import com.jft.market.model.OrderCart;
import com.jft.market.model.PaymentInstrument;
import com.litle.sdk.generate.SaleResponse;

public interface PurchaseService {

	public PaymentResponseWS purchaseProduct(String customerUuid, String orderCartUuid);

	public Customer getCustomer(String customerUuid);

	public OrderCart getOrderCart(String orderCartUuid);

	public void associateOrderCartAndCustomerWithPurchaseOrder(OrderCart orderCart, Customer customer);

	public List<PaymentInstrument> getActivePaymentInstruments(Customer customer);

	public PaymentResponseWS buildPaymentResponse(SaleResponse saleResponse);


	public void createPurchaseOrder(OrderCart orderCart, Customer customer);

}
