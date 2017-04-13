package com.jft.market.api.controllers;


import java.util.ArrayList;
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

import com.jft.market.api.ApiConstants;
import com.jft.market.api.BeanAttribute;
import com.jft.market.api.ws.CustomerWS;
import com.jft.market.api.ws.EmberResponse;
import com.jft.market.api.ws.SuccessWS;
import com.jft.market.api.ws.UserWS;
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
		BeanAttribute customerBeanAttribute = new BeanAttribute(ApiConstants.getSucessId(), new SuccessWS(ApiConstants.SUCCESS), ApiConstants.REGISTRATION);
		return new ResponseEntity(new EmberResponse<>(customerBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readCustomer(@PathVariable("customerUuid") String customerUuid) {
		log.info("Reading customer");
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		if (customer == null) {
			throw new EntityNotFoundException(ExceptionConstants.CUSTOMER_NOT_FOUND);
		}
		CustomerWS customerWS = customerService.convertEntityToWS(customer);
		BeanAttribute customerBeanAttribute = new BeanAttribute(customerWS.getUuid(), customerWS, ApiConstants.CUSTOMERS);
		return new ResponseEntity(new EmberResponse<>(customerBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readCustomers() {
		List<CustomerWS> customers = customerService.getAllCustomers();
		if (customers == null) {
			throw new EntityNotFoundException(ExceptionConstants.CUSTOMER_NOT_FOUND);
		}
		List<BeanAttribute> customerBeanAttributes = new ArrayList<>();
		customers.forEach(customerWS -> {
			BeanAttribute customerBeanAttribute = new BeanAttribute(customerWS.getUuid(), customerWS, ApiConstants.CUSTOMERS);
			customerBeanAttributes.add(customerBeanAttribute);
		});
		return new ResponseEntity(new EmberResponse<>(customerBeanAttributes), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteCustomer(@PathVariable("customerUuid") String customerUuid) {
		Customer customer = customerService.readCustomerByUuid(customerUuid);
		if (customer == null) {
			throw new EntityNotFoundException(ExceptionConstants.CUSTOMER_NOT_FOUND);
		}
		customerService.deleteCustomer(customer);
		BeanAttribute customerBeanAttribute = new BeanAttribute(customerUuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.CUSTOMER);
		return new ResponseEntity(new EmberResponse<>(customerBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity updateCustomer(@RequestBody CustomerWS customerWS, @PathVariable("customerUuid") String cutomerUuid) {
		customerService.validateCustomerWS(customerWS);
		customerService.updateCustomer(customerWS, cutomerUuid);
		UserWS userWS = customerService.readUpdatedCustmerWS(cutomerUuid);
		BeanAttribute customerBeanAttribute = new BeanAttribute(cutomerUuid, userWS, ApiConstants.CUSTOMER);
		return new ResponseEntity(new EmberResponse<>(customerBeanAttribute), HttpStatus.OK);
	}
}
