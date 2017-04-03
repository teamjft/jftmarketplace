package com.jft.market.util;

import com.jft.market.exceptions.InternalApiException;

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
}
