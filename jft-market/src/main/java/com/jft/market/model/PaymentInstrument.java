package com.jft.market.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jft.market.api.CreditCardDataEncrypter;
import com.jft.market.api.CreditCardTypes;
import com.jft.market.api.PaymnetInstrumentStatus;

@Entity
@Table(name = "payment_instrument")
@Setter
@Getter
@NoArgsConstructor
public class PaymentInstrument extends TimestampedFieldObject {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	@Column(name = "uuid", unique = true)
	private String uuid;

	@Column(name = "credit_card_number")
	private String creditCardNumber;

	@Column(name = "credit_card_type")
	@Enumerated(EnumType.STRING)
	private CreditCardTypes type;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private PaymnetInstrumentStatus status;

	@Column(name = "name_on_card")
	private String nameOnCard;

	@Column(name = "exp_month")
	private Long expirationMonth;

	@Column(name = "exp_year")
	private Long expirationYear;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "street1")
	private String street1;

	@Column(name = "street2")
	private String street2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip")
	private String zip;

	@Column(name = "country")
	private String country;

	@Column(name = "cvv")
	private String cvv;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public String getCreditCardNumber() throws Exception {
		CreditCardDataEncrypter creditCardDataEncrypter = new CreditCardDataEncrypter("credit-card-password");
		return creditCardDataEncrypter.decrypt(this.creditCardNumber);
	}

	public void setCreditCardNumber(String creditCartNumber) throws Exception {
		CreditCardDataEncrypter creditCardDataEncrypter = new CreditCardDataEncrypter("credit-card-password");
		this.creditCardNumber = creditCardDataEncrypter.encrypt(creditCartNumber);
	}

	public String getCvv() throws Exception {
		CreditCardDataEncrypter creditCardDataEncrypter = new CreditCardDataEncrypter("credit-card-password");
		return creditCardDataEncrypter.decrypt(this.cvv);
	}

	public void setCvv(String cvv) throws Exception {
		CreditCardDataEncrypter creditCardDataEncrypter = new CreditCardDataEncrypter("credit-card-password");
		this.cvv = creditCardDataEncrypter.encrypt(cvv);
	}
}
