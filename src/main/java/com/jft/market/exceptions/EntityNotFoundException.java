package com.jft.market.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {

	public String message;

	public EntityNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
