package com.jft.market.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeanAttribute<T> {

	private String type;
	private T id;
	private T attributes;

	public BeanAttribute(T id, T successWS, String type) {
		this.id = id;
		this.type = type;
		this.attributes = successWS;
	}
}
