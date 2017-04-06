package com.jft.market.api.ws;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateCustomerWS {

	private Long id;
	@NotNull
	private String username;
	@NotNull
	private String email;
	@NotNull
	private String customerUuid;
	@NotNull
	private Long phone;
}
