package com.jft.market.bindings;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class ErrorResource {
	private String message;
	private List<FieldErrorResource> fieldErrors;

	public ErrorResource(String message) {
		this.message = message;
	}
}