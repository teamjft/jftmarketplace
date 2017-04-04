package com.jft.market.api.ws;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentResponseWS {

	private HttpStatus status;
	private String message;

	public PaymentResponseWS(String message, HttpStatus status) {
		this.status = status;
		this.message = message;
	}
}
