package com.jft.market.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.api.ApiConstants;
import com.jft.market.api.BeanAttribute;
import com.jft.market.api.ws.EmberResponse;
import com.jft.market.api.ws.SuccessWS;
import com.jft.market.service.PurchaseService;

@RestController
@CrossOrigin
public class PurchaseApiImpl implements PurchaseApi {

	@Autowired
	private PurchaseService purchaseService;

	@Override
	public ResponseEntity purchaseProduct(@PathVariable("customerUuid") String customerUuid, @PathVariable("orderCartUuid") String orderCartUuid) {
		purchaseService.purchaseProduct(customerUuid, orderCartUuid);
		BeanAttribute paymentInstrumentBeanAttribute = new BeanAttribute(ApiConstants.getSucessId(), new SuccessWS(ApiConstants.SUCCESS), ApiConstants.PAYMENT_INSTRUMENT);
		return new ResponseEntity(new EmberResponse<>(paymentInstrumentBeanAttribute), HttpStatus.OK);
	}
}
