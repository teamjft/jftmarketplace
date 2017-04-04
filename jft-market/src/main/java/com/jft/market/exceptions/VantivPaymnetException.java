package com.jft.market.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VantivPaymnetException extends RuntimeException {

	public VantivPaymnetException(String message) {
		super(message);
	}
}
