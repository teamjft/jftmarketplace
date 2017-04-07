package com.jft.market.util;

import com.jft.market.exceptions.InternalApiException;
import com.jft.market.exceptions.VantivPaymnetException;

public class Preconditions {

	public static void check(boolean condition, String message) {
		if (condition) {
			throw new InternalApiException(message);
		}
	}

	public static <T extends RuntimeException> void check(boolean condition, T exceptionClass) {
		if (condition) {
			throw exceptionClass;
		}
	}

	public static void checkPaymentResponse(boolean condition, String message) {
		if (condition) {
			throw new VantivPaymnetException(message);
		}
	}
}
