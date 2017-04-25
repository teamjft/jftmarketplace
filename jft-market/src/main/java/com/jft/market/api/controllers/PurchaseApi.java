package com.jft.market.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(BaseApi.BASE_PATH + PurchaseApi.PURCHASE)
public interface PurchaseApi {

	String PURCHASE = "/v1/purchase";

	@RequestMapping(value = {"/customer/{customerUuid}/orderCart/{orderCartUuid}"},
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST
	)
	public
	@ResponseBody
	ResponseEntity purchaseProduct(@PathVariable("customerUuid") String customerUuid,
								   @PathVariable("orderCartUuid") String orderCartUuid);
}
