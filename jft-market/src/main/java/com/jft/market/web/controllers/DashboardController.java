package com.jft.market.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller(value = "dashboard")
public class DashboardController {

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	/*@Secured({"ROLE_USER", "ROLE_ADMIN"})*/
	@PreAuthorize("hasRole('ROLE_USER')")
	public String home() {
		System.out.println("Into dashboard home method");
		return "home";
	}
}
