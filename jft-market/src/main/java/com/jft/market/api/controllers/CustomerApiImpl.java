package com.jft.market.api.controllers;


import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.api.ws.CustomerWS;
import com.jft.market.api.ws.UpdateCustomerWS;
import com.jft.market.exceptions.EntityAlreadyExist;
import com.jft.market.exceptions.EntityNotFoundException;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.model.Customer;
import com.jft.market.service.CustomerService;
import com.jft.market.service.UserService;


@Slf4j
@RestController
public class CustomerApiImpl implements CustomerApi {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity createCustomer(@Valid @RequestBody CustomerWS customerWS, BindingResult bindingResult) {
		log.info("Validating customer payload");
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		Customer customer = customerService.readCustomerByEmailId(customerWS);
		if (customer != null) {
			throw new EntityAlreadyExist("Customer already exist.");
		}
		customerService.convertWStoEntityAndSave(customerWS);
		return null;
	}

	@Override
	public ResponseEntity readCustomer(@PathVariable("customerUuid") String customerUuid) {
		log.info("Reading customer");
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		if (customer == null) {
			throw new EntityNotFoundException("Customer not found.");
		}
		CustomerWS customerWS = customerService.convertEntityToWS(customer);
		return new ResponseEntity(customerWS, HttpStatus.OK);
	}

	@Override
	public ResponseEntity readCustomers() {
		List<CustomerWS> customers = customerService.getAllCustomers();
		if (customers == null) {
			throw new EntityNotFoundException("No Customer found");
		}
		return new ResponseEntity(customers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteCustomer(@PathVariable("customerUuid") String customerUuid) {
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		if (customer == null) {
			throw new EntityNotFoundException("Customer not found.");
		}
		customerService.deleteCustomer(customer);
		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public ResponseEntity updateCustomer(@Valid @RequestBody UpdateCustomerWS customerWS, BindingResult bindingResult) {
		log.info("Validating customer payload");
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		Customer customer = customerService.readCustomerByUuid(customerWS.getCustomerUuid());
		if (customer == null) {
			throw new EntityNotFoundException("Customer not found.");
		}
		customerService.updateCustomer(customer, customerWS);
		return null;
	}


}
