package com.jft.market.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.service.PurchaseService;

@RestController
public class PurchaseApiImpl implements PurchaseApi {

	@Autowired
	private PurchaseService purchaseService;

	@Override
	public ResponseEntity purchaseProduct(@PathVariable("customerUuid") String customerUuid, @PathVariable("productUuid") String productUuid) {
		purchaseService.purchaseProduct(customerUuid, productUuid);
		return null;
	}
}
