package com.jft.market.api.ws;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWS {

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long id;
	@NotNull
	private String username;
	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@NotNull
	private String email;
	@NotNull
	private String gender;
	private String uuid;
	@NotNull
	private Long phone;

	/*@NotNull
	private List<RoleWS> roles = new ArrayList<RoleWS>();
*/
	@NotNull
	private List<String> roles = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private CustomerWS customerWS;

	private Boolean enabled = Boolean.FALSE;
}
