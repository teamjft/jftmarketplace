package com.jft.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.ws.CustomerWS;
import com.jft.market.api.ws.PaymentInstrumentWS;
import com.jft.market.api.ws.UserWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Customer;
import com.jft.market.model.PaymentInstrument;
import com.jft.market.model.User;
import com.jft.market.repository.CustomerRepository;
import com.jft.market.repository.PaymentInstrumentRepository;
import com.jft.market.repository.UserRepository;
import com.jft.market.util.Preconditions;

@Service("paymentInstrumentService")
public class PaymentInstrumentServiceImpl implements PaymentInstrumentService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	PaymentInstrumentRepository paymentInstrumentRepository;

	@Override
	public CustomerWS createCustomerWSfromPaymentInstrumentWS(PaymentInstrumentWS paymentInstrumentWS) {
		Preconditions.check(paymentInstrumentWS == null, ExceptionConstants.PAYMENT_INSTRUMENT_NOT_FOUND);
		CustomerWS customerWS = new CustomerWS();
		customerWS.setCustomerName(paymentInstrumentWS.getCustomerName());
		customerWS.setUuid(paymentInstrumentWS.getCustomerUuid());
		return customerWS;
	}

	@Override
	public Long getUserId(UserWS userWS) {
		User user = userRepository.findByUuid(userWS.getUuid());
		return user != null ? user.getId() : null;
	}

	@Override
	@Transactional
	public void createAndSavePaymentInstrument(PaymentInstrumentWS paymentInstrumentWS) {
		CustomerWS customerWS = createCustomerWSfromPaymentInstrumentWS(paymentInstrumentWS);
		Customer customer = customerRepository.findByUuid(customerWS.getUuid());
		Preconditions.check(customer == null, ExceptionConstants.CUSTOMER_NOT_FOUND);
		PaymentInstrument paymentInstrument = convertAndLinkCustomerWithPaymentInstrument(paymentInstrumentWS, customer);
		if (StringUtils.isEmpty(paymentInstrument.getUuid())) {
			paymentInstrument.setUuid(UUID.randomUUID().toString());
		}
		paymentInstrumentRepository.save(paymentInstrument);
	}

	@Override
	public PaymentInstrument convertAndLinkCustomerWithPaymentInstrument(PaymentInstrumentWS paymentInstrumentWS, Customer customer) {
		if (paymentInstrumentWS != null) {
			PaymentInstrument paymentInstrument = new PaymentInstrument();
			paymentInstrument.setUuid(paymentInstrumentWS.getPaymentInstrumentUuid());
			paymentInstrument.setCity(paymentInstrumentWS.getCity());
			paymentInstrument.setCountry(paymentInstrumentWS.getCountry());
			paymentInstrument.setCreditCartNumber(paymentInstrumentWS.getCreditCartNumber());
			paymentInstrument.setType(paymentInstrumentWS.getType());
			paymentInstrument.setDeleted(Boolean.FALSE);
			paymentInstrument.setNameOnCard(paymentInstrumentWS.getNameOnCard());
			paymentInstrument.setExpirationMonth(paymentInstrumentWS.getExpirationMonth());
			paymentInstrument.setExpirationYear(paymentInstrumentWS.getExpirationYear());
			paymentInstrument.setFirstName(paymentInstrumentWS.getFirstName());
			paymentInstrument.setLastName(paymentInstrumentWS.getLastName());
			paymentInstrument.setPhoneNumber(paymentInstrumentWS.getPhoneNumber());
			paymentInstrument.setState(paymentInstrumentWS.getState());
			paymentInstrument.setStreet1(paymentInstrumentWS.getStreet1());
			paymentInstrument.setStreet2(paymentInstrumentWS.getStreet2());
			paymentInstrument.setZip(paymentInstrumentWS.getZip());

			paymentInstrument.setCustomer(customer);
			customer.getPaymentInstrumentList().add(paymentInstrument);

			return paymentInstrument;
		}
		return null;
	}

	@Override
	@Transactional
	public List<PaymentInstrumentWS> readPaymentInstruments(String customerUuid) {
		Customer customer = customerRepository.findByUuid(customerUuid);
		Preconditions.check(customer == null, ExceptionConstants.CUSTOMER_NOT_FOUND);
		List<PaymentInstrument> paymentInstrumentList = paymentInstrumentRepository.findByCustomerUuid(customerUuid);
		return convertPaymentInstrumentListToWSList(paymentInstrumentList);

	}

	@Override
	@Transactional
	public void readAndDeletePaymentInstrument(String paymentinstrumentUuid) {
		PaymentInstrument paymentInstrument = paymentInstrumentRepository.findByUuid(paymentinstrumentUuid);
		Preconditions.check(paymentInstrument == null, ExceptionConstants.PAYMENT_INSTRUMENT_NOT_FOUND);
		paymentInstrumentRepository.delete(paymentInstrument);
	}

	@Override
	public List<PaymentInstrumentWS> convertPaymentInstrumentListToWSList(List<PaymentInstrument> paymentInstruments) {
		Preconditions.check(paymentInstruments.isEmpty(), ExceptionConstants.PAYMENT_INSTRUMENT_NOT_FOUND);
		List<PaymentInstrumentWS> paymentInstrumentWsList = new ArrayList<>();
		paymentInstruments.forEach(paymentInstrument -> {
			PaymentInstrumentWS paymentInstrumentWS = new PaymentInstrumentWS();
			paymentInstrumentWS.setCustomerName(paymentInstrument.getFirstName());
			paymentInstrumentWS.setCity(paymentInstrument.getCity());
			paymentInstrumentWS.setCountry(paymentInstrument.getCountry());
			paymentInstrumentWS.setCreditCartNumber(paymentInstrument.getCreditCartNumber());
			paymentInstrumentWS.setCustomerUuid(paymentInstrument.getCustomer().getUuid());
			paymentInstrumentWS.setDeleted(paymentInstrument.isDeleted());
			paymentInstrumentWS.setExpirationMonth(paymentInstrument.getExpirationMonth());
			paymentInstrumentWS.setFirstName(paymentInstrument.getFirstName());
			paymentInstrumentWS.setLastName(paymentInstrument.getLastName());
			paymentInstrumentWS.setPaymentInstrumentUuid(paymentInstrument.getUuid());
			paymentInstrumentWS.setZip(paymentInstrument.getZip());
			paymentInstrumentWS.setType(paymentInstrument.getType());
			paymentInstrumentWS.setStreet1(paymentInstrument.getStreet1());
			paymentInstrumentWS.setStreet2(paymentInstrument.getStreet2());
			paymentInstrumentWS.setPhoneNumber(paymentInstrument.getPhoneNumber());
			paymentInstrumentWS.setExpirationYear(paymentInstrument.getExpirationYear());
			paymentInstrumentWS.setCustomerName(paymentInstrument.getCustomer().getName());
			paymentInstrumentWS.setState(paymentInstrument.getState());
			paymentInstrumentWS.setNameOnCard(paymentInstrument.getNameOnCard());
			paymentInstrumentWsList.add(paymentInstrumentWS);
		});
		return paymentInstrumentWsList;
	}
}
