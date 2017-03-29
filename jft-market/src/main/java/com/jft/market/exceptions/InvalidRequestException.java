package com.jft.market.exceptions;

import lombok.Getter;

import org.springframework.validation.Errors;

@Getter
public class InvalidRequestException extends RuntimeException {

	private Errors errors;

	public InvalidRequestException(Errors errors) {
		this.errors = errors;
	}
}
