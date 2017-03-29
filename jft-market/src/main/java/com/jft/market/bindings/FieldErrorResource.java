package com.jft.market.bindings;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class FieldErrorResource {
	private String field;
	private String code;
	private String message;
}