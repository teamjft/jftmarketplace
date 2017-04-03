package com.jft.market.api;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBean {

	private Long Id;
	private String username;
	private String password;
	private Boolean enabled;
	private String email;
	private String gender;
	private String uuid;

}
