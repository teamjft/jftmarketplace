package com.jft.market.api.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import com.jft.market.api.ws.EmberResponse;
import com.jft.market.api.ws.PaymentInstrumentWS;
import com.jft.market.api.ws.SuccessWS;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.service.PaymentInstrumentService;

@RestController
@CrossOrigin
public class PaymentInstrumentApiimpl implements PaymentInstrumentApi {

	@Autowired
	PaymentInstrumentService paymentInstrumentService;

	@Override
	public ResponseEntity createPaymentInstrument(@Valid @RequestBody PaymentInstrumentWS paymentInstrumentWS, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		try {
			paymentInstrumentService.createAndSavePaymentInstrument(paymentInstrumentWS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BeanAttribute paymentInstrumentBeanAttribute = new BeanAttribute(ApiConstants.getSucessId(), new SuccessWS(ApiConstants.SUCCESS), ApiConstants.PAYMENT_INSTRUMENT);
		return new ResponseEntity(new EmberResponse<>(paymentInstrumentBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readPaymentInstrument(@PathVariable("customerUuid") String customerUuid) {
		List<PaymentInstrumentWS> paymentInstruments = paymentInstrumentService.readPaymentInstruments(customerUuid);
		List<BeanAttribute> paymentInstrumentBeanAttributes = new ArrayList<>();
		paymentInstruments.forEach(paymentInstrumentWS -> {
			BeanAttribute paymentInstrumentBeanAttribute = new BeanAttribute(paymentInstrumentWS.getPaymentInstrumentUuid(), paymentInstrumentWS, ApiConstants.PAYMENT_INSTRUMENT);
			paymentInstrumentBeanAttributes.add(paymentInstrumentBeanAttribute);
		});
		return new ResponseEntity(new EmberResponse<>(paymentInstrumentBeanAttributes), HttpStatus.OK);

	}

	@Override
	public ResponseEntity deletPaymnetInstrument(@PathVariable("paymentInstrumentUuid") String paymentinstrumentUuid) {
		paymentInstrumentService.readAndDeletePaymentInstrument(paymentinstrumentUuid);
		BeanAttribute paymentInstrumentBeanAttribute = new BeanAttribute(ApiConstants.getSucessId(), new SuccessWS(ApiConstants.SUCCESS), ApiConstants.PAYMENT_INSTRUMENT);
		return new ResponseEntity(new EmberResponse<>(paymentInstrumentBeanAttribute), HttpStatus.OK);
	}
}
