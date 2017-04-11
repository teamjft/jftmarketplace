package com.jft.market.api.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "success")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuccessWS {

	private String status;

	public SuccessWS(String status) {
		this.status = status;
	}
}
