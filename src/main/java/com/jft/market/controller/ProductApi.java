package com.jft.market.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jft.market.DataHelper.ProductHelper;
import com.jft.market.model.ProductWS;

@Slf4j
@Controller
public class ProductApi {

	@RequestMapping(value = {"createProduct"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createProduct(ProductWS productWS) {
		log.info("** Validating Product payload ***");
		ProductHelper.validate(productWS);
	}
}
