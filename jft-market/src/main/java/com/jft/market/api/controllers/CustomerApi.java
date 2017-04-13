package com.jft.market.api.controllers;


import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jft.market.api.ws.CustomerWS;

@RequestMapping(value = BaseApi.BASE_PATH + CustomerApi.CUSTOMER)
public interface CustomerApi {

	String CUSTOMER = "v1/customer";
	String CUSTOMERS = "customers";

	@RequestMapping(value = {"create"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createCustomer(@Valid @RequestBody CustomerWS customerWS, BindingResult bindingResult);

	@RequestMapping(value = {"{customerUuid}"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readCustomer(@PathVariable("customerUuid") String customerUuid);

	@RequestMapping(value = {CUSTOMERS},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readCustomers();

	@RequestMapping(value = {"delete/{customerUuid}"},
			method = RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity deleteCustomer(@PathVariable("customerUuid") String customerUuid);


	@RequestMapping(value = {"update/{customerUuid}"},
			method = RequestMethod.PATCH,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity updateCustomer(@RequestBody CustomerWS customerWS,
								  @PathVariable("customerUuid") String cutomerUuid);
}
