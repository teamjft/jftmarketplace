package com.jft.market.service;


import java.util.Optional;
import java.util.Set;

import com.jft.market.api.ws.OrderCartWS;
import com.jft.market.model.Customer;
import com.jft.market.model.OrderCart;

public interface OrderCartService {

	public OrderCartWS createEmptyOrderCartForCustomer(String customerUuid);

	public Optional<OrderCart> checkIfCustomerAlreadyHasInitializedOrderCart(Customer customer);

	public Set<OrderCart> findCustomerOrderCarts(String customerUuid);

	public OrderCart createAndAssociateOrderCart(Customer customer);

	public OrderCartWS convertEntityToWS(OrderCart orderCart);

	public String createOrderCart(String customerUuid, String productUuid);

	public String associateProductWithOrderCart(OrderCartWS orderCartWS, String productUuid);

	public void removeProductFromOrderCart(String orderCartUuid, String productUuid);
}
