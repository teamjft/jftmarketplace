package com.jft.market.api.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.api.ApiConstants;
import com.jft.market.api.BeanAttribute;
import com.jft.market.api.ws.EmberResponse;
import com.jft.market.api.ws.OrderCartWS;
import com.jft.market.api.ws.SuccessWS;
import com.jft.market.service.OrderCartService;

@Slf4j
@RestController
public class OrderCartApiImpl implements OrderCartApi {

	@Autowired
	private OrderCartService orderCartService;

	@Override
	public ResponseEntity createEmptyOrderCartForCustomer(@PathVariable("customerUuid") String customerUuid) {
		OrderCartWS orderCartWS = orderCartService.createEmptyOrderCartForCustomer(customerUuid);
		BeanAttribute orderCartBeanAttribute = new BeanAttribute(orderCartWS.getUuid(), orderCartWS, ApiConstants.ORDERCART);
		return new ResponseEntity(new EmberResponse<>(orderCartBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity createOrderCartForCustomer(@PathVariable("customerUuid") String customerUuid, @PathVariable("productUuid") String productUuid) {
		String orderCartUuid = orderCartService.createOrderCart(customerUuid, productUuid);
		BeanAttribute orderCartBeanAttribute = new BeanAttribute(orderCartUuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.ORDERCART);
		return new ResponseEntity(new EmberResponse<>(orderCartBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteProductFromOrderCart(@PathVariable("ordercartUuid") String ordercartUuid, @PathVariable("productUuid") String productUuid) {
		orderCartService.removeProductFromOrderCart(ordercartUuid, productUuid);
		BeanAttribute orderCartBeanAttribute = new BeanAttribute(ordercartUuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.ORDERCART);
		return new ResponseEntity(new EmberResponse<>(orderCartBeanAttribute), HttpStatus.OK);
	}
}
