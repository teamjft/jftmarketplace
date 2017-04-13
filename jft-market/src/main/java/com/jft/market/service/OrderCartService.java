package com.jft.market.service;


import com.jft.market.api.ws.OrderCartWS;
import com.jft.market.model.Customer;
import com.jft.market.model.OrderCart;

public interface OrderCartService {

	public OrderCartWS createEmptyOrderCart(String customerUuid);

	public Boolean checkIfCustomerAlreadyHasCart(Customer customer);

	public OrderCart createAndAssociateOrderCart(Customer customer);

	public OrderCartWS convertEntityToWS(OrderCart orderCart);

	public String createOrderCart(String customerUuid, String productUuid);

	public String associateProductWithOrderCart(OrderCartWS orderCartWS, String productUuid);
}
