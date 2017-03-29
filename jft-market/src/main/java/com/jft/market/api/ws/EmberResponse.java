package com.jft.market.api.ws;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class EmberResponse<T> {

	private T data;

	public EmberResponse(T payload) {
		this.data = payload;
	}
}
