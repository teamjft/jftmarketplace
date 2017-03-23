package com.jft.market.DataHelper;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "error")
public class ErrorWS implements Serializable {
	private static final long serialVersionUID = -8085773744616296530L;

	private String code;
	private String message;
	private Object details;

	public ErrorWS(Object code, String message) {
		this(code, message, null);
	}

	public ErrorWS(Object code, String message, Object details) {
		this.code = code.toString();
		this.message = message;
		this.details = details;
	}
}