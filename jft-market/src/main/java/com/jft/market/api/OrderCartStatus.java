package com.jft.market.api;


public enum OrderCartStatus {

	INITIALIZED,
	CANCELLED,
	SUSPENDED,
	ACTIVE;

	public boolean isInitialized() {
		return this == INITIALIZED;
	}

	public boolean isCanceled() {
		return this == CANCELLED;
	}

	public boolean isSuspended() {
		return this == SUSPENDED;
	}

	public boolean isActive() {
		return this == ACTIVE;
	}

}
