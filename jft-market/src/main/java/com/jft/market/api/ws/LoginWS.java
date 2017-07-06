package com.jft.market.api.ws;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LoginWS {

	private String username;
	private String password;
}
