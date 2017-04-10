package com.jft.market.api.ws;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWS {

	@NotNull
	private String fname;
	@NotNull
	private String lname;
	@NotNull
	private String email;
	@NotNull
	private String gender;
	private String uuid;
	@NotNull
	private Long phone;

	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private UserWS userWS;

	/*private Boolean enabled = Boolean.FALSE;*/
}
