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

import com.jft.market.api.ws.PaymentInstrumentWS;

@RequestMapping(BaseApi.BASE_PATH + PaymentInstrumentApi.PAYMENT_INSTRUMENT)
public interface PaymentInstrumentApi {

	String PAYMENT_INSTRUMENT = "/v1/paymentInstrument";


	@RequestMapping(value = {"create"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createPaymentInstrument(@Valid @RequestBody PaymentInstrumentWS paymentInstrumentWS, BindingResult bindingResult);

	@RequestMapping(value = {"read/{customerUuid}"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readPaymentInstrument(@PathVariable("customerUuid") String customerUuid);

	@RequestMapping(value = {"delete/{paymentInstrumentUuid}"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity deletPaymnetInstrument(@PathVariable("paymentInstrumentUuid") String paymentInstrumentUuid);

}
