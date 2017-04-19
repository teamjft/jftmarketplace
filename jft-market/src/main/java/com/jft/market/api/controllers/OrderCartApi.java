package com.jft.market.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = BaseApi.BASE_PATH + OrderCartApi.ORDER_CART)
public interface OrderCartApi {

	String ORDER_CART = "v1/ordercart";
	String ORDER_CARTS = "ordercarts";

	@RequestMapping(value = {"create/customer/{customerUuid}"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createEmptyOrderCartForCustomer(@PathVariable("customerUuid") String customerUuid);

	@RequestMapping(value = {"customer/{customerUuid}/product/{productUuid}"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createOrderCartForCustomer(@PathVariable("customerUuid") String customerUuid,
											  @PathVariable("productUuid") String productUuid);


	@RequestMapping(value = {"{ordercartUuid}/remove/product/{productUuid}"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity deleteProductFromOrderCart(@PathVariable("ordercartUuid") String ordercartUuid,
											  @PathVariable("productUuid") String productUuid);
}
