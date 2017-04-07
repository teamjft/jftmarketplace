package com.jft.market.service;


import java.util.List;

import com.jft.market.api.ws.PaymentResponseWS;
import com.jft.market.model.Customer;
import com.jft.market.model.PaymentInstrument;
import com.jft.market.model.Product;
import com.jft.market.model.PurchaseOrder;
import com.litle.sdk.generate.SaleResponse;

public interface PurchaseService {

	public PaymentResponseWS purchaseProduct(String customerUuid, String productUuid);

	public Customer getCustomer(String customerUuid);

	public Product getProduct(String productUuid);

	public void associateCustomerWithOrder(Product product, Customer customer);

	public PaymentInstrument getPaymentInstrument(String customerUuid);

	public PaymentResponseWS buildPaymentResponse(SaleResponse saleResponse);

	public List<PurchaseOrder> getOrdersByProductId(Product product);

	public void createOrder(Customer customer, Product product);

}
