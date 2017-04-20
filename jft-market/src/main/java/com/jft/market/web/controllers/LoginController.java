package com.jft.market.web.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jft.market.api.ws.LoginWS;

@Slf4j
@Controller
public class LoginController {

	private static final String LOGIN = "login/";

	@RequestMapping(value = {"login"}, method = RequestMethod.GET)
	public String login() {
		log.info("***Into Login method****");
		return LOGIN + "login";
	}

	@RequestMapping(value = {"login"}, method = RequestMethod.POST)
	public String submitLogin(LoginWS login) {
		log.info("***Into Login submit method****");
		return LOGIN + "login";
	}

	@RequestMapping(value = {"loginFailed"}, method = RequestMethod.GET)
	public String loginFailed(ModelMap model) {
		log.info("***Into loginFailed method****");
		model.addAttribute("error", true);
		return LOGIN + "login";

	}
}
