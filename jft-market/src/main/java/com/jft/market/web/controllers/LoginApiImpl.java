package com.jft.market.web.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.jft.market.configuration.CustomeAuthenticationProvider;

@Slf4j
@Controller
public class LoginApiImpl implements LoginApi {

	String LOGIN = "login/";

	@Override
	public String login() {
		CustomeAuthenticationProvider customeAuthenticationProvider = new CustomeAuthenticationProvider();
		if (customeAuthenticationProvider.isUserAlreadyLoggedIn()) {
			return "home";
		} else {
			return LOGIN + "login";
		}
	}

	@Override
	public String loginFailed(ModelMap model) {
		model.addAttribute("error", true);
		return LOGIN + "login";

	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public String homePage() {
		return "home";
	}
}
