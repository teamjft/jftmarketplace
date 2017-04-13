package com.jft.market.api.ws;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jft.market.api.OrderCartStatus;

@Setter
@Getter
@XmlRootElement(name = "order_cart")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderCartWS {

	@NotNull
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String uuid;
	@NotNull
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private OrderCartStatus status;
}
