package com.jft.market.api;


import java.util.Set;

import com.google.common.collect.Sets;

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

	public static final Set<OrderCartStatus> ACTIVE_ORDER_CARTS = Sets.immutableEnumSet(ACTIVE, INITIALIZED);

}
