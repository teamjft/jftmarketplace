package com.jft.market.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class LoginController {

	@RequestMapping(value = {"login"}, method = RequestMethod.GET)
	public String login() {
		log.info("***Into Login method****");
		return "login/login";
	}

	@RequestMapping(value = {"loginFailed"}, method = RequestMethod.GET)
	public String loginFailed(ModelMap model) {
		log.info("***Into loginFailed method****");
		model.addAttribute("error", true);
		return "login/login";

	}

	/*@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		return "login/login";
	}*/
}
