package com.jft.market.api.controllers;


import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.api.ws.CustomerWS;
import com.jft.market.api.ws.ResponseStatus;
import com.jft.market.exceptions.EntityAlreadyExist;
import com.jft.market.exceptions.EntityNotFoundException;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.model.Customer;
import com.jft.market.service.CustomerService;
import com.jft.market.service.UserService;


@Slf4j
@CrossOrigin
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
			throw new EntityAlreadyExist(ExceptionConstants.CUSTOMER_ALREADY_EXISTS);
		}
		customerService.convertWStoEntityAndSave(customerWS);
		return ResponseStatus.SUCCESS.getResponse();
	}

	@Override
	public ResponseEntity readCustomer(@PathVariable("customerUuid") String customerUuid) {
		log.info("Reading customer");
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		if (customer == null) {
			throw new EntityNotFoundException(ExceptionConstants.CUSTOMER_NOT_FOUND);
		}
		CustomerWS customerWS = customerService.convertEntityToWS(customer);
		return new ResponseEntity(customerWS, HttpStatus.OK);
	}

	@Override
	public ResponseEntity readCustomers() {
		List<CustomerWS> customers = customerService.getAllCustomers();
		if (customers == null) {
			throw new EntityNotFoundException(ExceptionConstants.CUSTOMER_NOT_FOUND);
		}
		return new ResponseEntity(customers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteCustomer(@PathVariable("customerUuid") String customerUuid) {
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		if (customer == null) {
			throw new EntityNotFoundException(ExceptionConstants.CUSTOMER_NOT_FOUND);
		}
		customerService.deleteCustomer(customer);
		return ResponseStatus.SUCCESS.getResponse();
	}

	@Override
	public ResponseEntity updateCustomer(@RequestBody CustomerWS customerWS, @PathVariable("customerUuid") String cutomerUuid) {
		customerService.validateCustomerWS(customerWS);
		Customer customer = customerService.readCustomerByUuid(cutomerUuid);
		if (customer == null) {
			throw new EntityNotFoundException(ExceptionConstants.CUSTOMER_NOT_FOUND);
		}
		customerService.updateCustomer(customer, customerWS);
		return ResponseStatus.SUCCESS.getResponse();
	}
}
