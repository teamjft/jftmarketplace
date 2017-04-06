package com.jft.market.service;


import java.util.List;

import com.jft.market.api.ws.CustomerWS;
import com.jft.market.api.ws.UpdateCustomerWS;
import com.jft.market.api.ws.UserWS;
import com.jft.market.model.Customer;

public interface CustomerService {

	public void convertWStoEntityAndSave(CustomerWS customerWS);

	public Customer convertWStoEntity(CustomerWS customerWS);

	public void createCustomer(Customer customer);

	public UserWS createUserWSFromCustomerWS(CustomerWS customerWS);

	public Customer readCustomerByEmailId(CustomerWS customerWS);

	public Customer readCustomerByUuid(String customerUuid);

	public CustomerWS convertEntityToWS(Customer customer);

	public List<CustomerWS> getAllCustomers();

	public List<CustomerWS> convertEntityListToWSList(List<Customer> customers);

	public void deleteCustomer(Customer customer);

	public Boolean isValidCustomer(Customer customer);

	public void updateCustomer(Customer customer, UpdateCustomerWS customerWS);

	public Customer checkAndUpdateCustomer(Customer customer, UpdateCustomerWS customerWS);
}
