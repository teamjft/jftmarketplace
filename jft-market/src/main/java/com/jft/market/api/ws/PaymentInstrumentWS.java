package com.jft.market.api.ws;


import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jft.market.api.CreditCardTypes;
import com.jft.market.api.PaymnetInstrumentStatus;

@Setter
@Getter
@NoArgsConstructor
public class PaymentInstrumentWS {

	@NotNull
	private String customerEmail;
	@NotNull
	private String customerUuid;

	/*@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String Id;*/
	private String paymentInstrumentUuid;
	@NotNull
	private String creditCardNumber;
	@NotNull
	private CreditCardTypes type;
	@NotNull
	private Long expirationMonth;
	@NotNull
	private Long expirationYear;
	@NotNull
	private Boolean deleted = false;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String phoneNumber;
	@NotNull
	private String street1;
	@NotNull
	private String street2;
	@NotNull
	private String city;
	@NotNull
	private String state;
	@NotNull
	private String zip;
	@NotNull
	private String country;
	@NotNull
	private String nameOnCard;
	@NotNull
	private String cvv;
	private PaymnetInstrumentStatus status;
}
