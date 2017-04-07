package com.jft.market.api.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.api.ws.PaymentInstrumentWS;
import com.jft.market.api.ws.ResponseStatus;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.service.PaymentInstrumentService;
import com.jft.market.service.PurchaseService;

@RestController
public class PaymentInstrumentApiimpl implements PaymentInstrumentApi {

	@Autowired
	PaymentInstrumentService paymentInstrumentService;

	@Autowired
	private PurchaseService purchaseService;

	@Override
	public ResponseEntity createPaymentInstrument(@Valid @RequestBody PaymentInstrumentWS paymentInstrumentWS, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		paymentInstrumentService.createAndSavePaymentInstrument(paymentInstrumentWS);
		return ResponseStatus.SUCCESS.getResponse();
	}

	@Override
	public ResponseEntity readPaymentInstrument(@PathVariable("customerUuid") String customerUuid) {
		List<PaymentInstrumentWS> paymentInstruments = paymentInstrumentService.readPaymentInstruments(customerUuid);
		return new ResponseEntity(paymentInstruments, HttpStatus.OK);
	}

	@Override
	public ResponseEntity deletPaymnetInstrument(@PathVariable("paymentInstrumentUuid") String paymentinstrumentUuid) {
		paymentInstrumentService.readAndDeletePaymentInstrument(paymentinstrumentUuid);
		return ResponseStatus.SUCCESS.getResponse();
	}
}
