package com.jft.market.service;

import java.util.List;

import com.jft.market.api.ws.CustomerWS;
import com.jft.market.api.ws.PaymentInstrumentWS;
import com.jft.market.api.ws.UserWS;
import com.jft.market.model.Customer;
import com.jft.market.model.PaymentInstrument;

public interface PaymentInstrumentService {

	public CustomerWS createCustomerWSfromPaymentInstrumentWS(PaymentInstrumentWS paymentInstrumentWS);

	public Long getUserId(UserWS userWS);

	public void createAndSavePaymentInstrument(PaymentInstrumentWS paymentInstrumentWS);

	public PaymentInstrument convertAndLinkCustomerWithPaymentInstrument(PaymentInstrumentWS paymentInstrumentWS, Customer customer);

	public List<PaymentInstrumentWS> readPaymentInstruments(String customerUuid);

	public void readAndDeletePaymentInstrument(String paymentinstrumentUuid);

	public List<PaymentInstrumentWS> convertPaymentInstrumentListToWSList(List<PaymentInstrument> paymentInstrument);

	public List<PaymentInstrument> getvalidPaymentInstruments(List<PaymentInstrument> paymentInstrument);
}
