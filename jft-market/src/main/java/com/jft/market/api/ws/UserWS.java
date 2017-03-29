package com.jft.market.api.ws;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserWS {

	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String email;
	@NotNull
	private String gender;

	private Boolean enabled = Boolean.FALSE;
}
