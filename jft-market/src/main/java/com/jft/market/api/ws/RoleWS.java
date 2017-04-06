package com.jft.market.api.ws;


import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "name")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Setter
@Getter
public class RoleWS {
	private String name;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	Set<UserWS> users = new HashSet<UserWS>();

	public RoleWS(String role) {
		this.name = role;
	}

	@NotEmpty
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String[] roles;
}
