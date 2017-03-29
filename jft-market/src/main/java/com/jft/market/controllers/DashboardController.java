package com.jft.market.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller(value = "dashboard")
public class DashboardController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "dashboard/home";
	}
}
