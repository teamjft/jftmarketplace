package com.jft.market.api;


public enum PaymnetInstrumentStatus {

	ACTIVE,
	SUSPENDED;

	public boolean isActive() {
		return this == ACTIVE;
	}

	public boolean isSuspended() {
		return this == SUSPENDED;
	}

}
