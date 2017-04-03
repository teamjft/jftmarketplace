package com.jft.market.api;


public enum CreditCardTypes {

	MASTER("master"),
	VISA("Visa");

	String type;

	CreditCardTypes(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}
