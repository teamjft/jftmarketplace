package com.jft.market.service;

import static com.jft.market.model.QPaymentInstrument.paymentInstrument;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.PaymnetInstrumentStatus;
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
import com.jft.market.util.EntityPredicates;
import com.jft.market.util.Preconditions;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

@Service("paymentInstrumentService")
public class PaymentInstrumentServiceImpl implements PaymentInstrumentService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PaymentInstrumentRepository paymentInstrumentRepository;

	@Autowired
	private CustomerService customerService;

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public CustomerWS createCustomerWSfromPaymentInstrumentWS(PaymentInstrumentWS paymentInstrumentWS) {
		Preconditions.check(paymentInstrumentWS == null, ExceptionConstants.PAYMENT_INSTRUMENT_NOT_FOUND);
		CustomerWS customerWS = new CustomerWS();
		customerWS.setEmail(paymentInstrumentWS.getCustomerEmail());
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
	public void createAndSavePaymentInstrument(PaymentInstrumentWS paymentInstrumentWS) throws Exception {
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
	public PaymentInstrument convertAndLinkCustomerWithPaymentInstrument(PaymentInstrumentWS paymentInstrumentWS, Customer customer) throws Exception {
		Preconditions.check(paymentInstrumentWS == null, ExceptionConstants.PAYMENT_INSTRUMENT_NOT_FOUND);
		PaymentInstrument paymentInstrument = new PaymentInstrument();
		paymentInstrument.setUuid(paymentInstrumentWS.getPaymentInstrumentUuid());
		paymentInstrument.setCity(paymentInstrumentWS.getCity());
		paymentInstrument.setCountry(paymentInstrumentWS.getCountry());
		paymentInstrument.setCreditCardNumber(paymentInstrumentWS.getCreditCardNumber());
		paymentInstrument.setCvv(paymentInstrumentWS.getCvv());
		paymentInstrument.setType(paymentInstrumentWS.getType());
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
		paymentInstrument.setStatus(PaymnetInstrumentStatus.ACTIVE);

			paymentInstrument.setCustomer(customer);
			customer.getPaymentInstrumentList().add(paymentInstrument);

			return paymentInstrument;
	}

	@Override
	@Transactional
	public List<PaymentInstrumentWS> readPaymentInstruments(String customerUuid) {
		Customer customer = customerRepository.findByUuid(customerUuid);
		Preconditions.check(customer == null, ExceptionConstants.CUSTOMER_NOT_FOUND);
		Preconditions.check(!customerService.isValidCustomer(customer), ExceptionConstants.CUSTOMER_NOT_ENABLED);
		List<PaymentInstrument> activePaymentInstruments = getActivePaymentInstruments(customer);
		return convertPaymentInstrumentListToWSList(activePaymentInstruments);

	}


	@Override
	@Transactional
	public List<PaymentInstrument> getActivePaymentInstruments(Customer customer) {
		JPQLQuery query = new JPAQuery(entityManager);
		Predicate activePaymnetInstruments = paymentInstrument.customer.eq(customer)
				.and(paymentInstrument.status.eq(PaymnetInstrumentStatus.ACTIVE))
				.and(EntityPredicates.isTimestampedFieldEnabledAndNotDeleted(paymentInstrument._super));
		return query.from(paymentInstrument).where(activePaymnetInstruments).list(paymentInstrument);
	}

	@Override
	@Transactional
	public void readAndDeletePaymentInstrument(String paymentinstrumentUuid) {
		PaymentInstrument paymentInstrument = paymentInstrumentRepository.findByUuid(paymentinstrumentUuid);
		Preconditions.check(paymentInstrument == null, ExceptionConstants.PAYMENT_INSTRUMENT_NOT_FOUND);
		paymentInstrument.setDeleted(Boolean.TRUE);
		paymentInstrument.setEnabled(Boolean.FALSE);
		paymentInstrumentRepository.save(paymentInstrument);
	}

	@Override
	public List<PaymentInstrumentWS> convertPaymentInstrumentListToWSList(List<PaymentInstrument> paymentInstruments) {
		Preconditions.check(paymentInstruments.isEmpty(), ExceptionConstants.PAYMENT_INSTRUMENT_NOT_FOUND);
		List<PaymentInstrumentWS> paymentInstrumentWsList = new ArrayList<>();
		paymentInstruments.forEach(paymentInstrument -> {
			PaymentInstrumentWS paymentInstrumentWS = new PaymentInstrumentWS();
			paymentInstrumentWS.setCustomerEmail(paymentInstrument.getCustomer().getEmail());
			paymentInstrumentWS.setCity(paymentInstrument.getCity());
			paymentInstrumentWS.setCountry(paymentInstrument.getCountry());
			try {
				paymentInstrumentWS.setCreditCardNumber(paymentInstrument.getCreditCardNumber());
			} catch (Exception e) {
				e.printStackTrace();
			}
			paymentInstrumentWS.setCustomerUuid(paymentInstrument.getCustomer().getUuid());
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
			paymentInstrumentWS.setState(paymentInstrument.getState());
			paymentInstrumentWS.setNameOnCard(paymentInstrument.getNameOnCard());
			paymentInstrumentWS.setStatus(PaymnetInstrumentStatus.ACTIVE);
			paymentInstrumentWsList.add(paymentInstrumentWS);
		});
		return paymentInstrumentWsList;
	}

	@Override
	public List<PaymentInstrument> getvalidPaymentInstruments(List<PaymentInstrument> paymentInstruments) {
		List<PaymentInstrument> validPaymentInstruments = new ArrayList<>();
		paymentInstruments.forEach(paymentInstrument -> {
			if (paymentInstrument.getEnabled().equals(Boolean.TRUE) && paymentInstrument.getDeleted().equals(Boolean.FALSE)) {
				validPaymentInstruments.add(paymentInstrument);
			}
		});
		return validPaymentInstruments;
	}

/*	@Override
	public Long splitAndEncrypyCardNumber(Long cardNumber) {
		String firstTenNumbers = passwordEncoder.encode(cardNumber.toString().substring(0, 9));
		String lastNumbers = cardNumber.toString().substring(10, cardNumber.toString().length());
		String encryptedCardNumber = firstTenNumbers + lastNumbers;
		return Long.valueOf(encryptedCardNumber);
	}*/
}
