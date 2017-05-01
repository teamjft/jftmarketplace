package com.jft.market.web.controllers;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "")
public interface LoginApi {

	String LOGIN = "login";
	String LOGIN_FAILED = "loginFailed";
	String HOMEPAGE = "homepage";

	@RequestMapping(value = {LOGIN}, method = RequestMethod.GET)
	public String login();

	@RequestMapping(value = {LOGIN_FAILED}, method = RequestMethod.GET)
	public String loginFailed(ModelMap modelMap);

	@RequestMapping(value = {HOMEPAGE}, method = RequestMethod.GET)
	public String homePage();
}
