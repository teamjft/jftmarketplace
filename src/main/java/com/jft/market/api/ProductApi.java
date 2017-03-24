package com.jft.market.api;


import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jft.market.model.ProductWS;

public interface ProductApi {

	@RequestMapping(value = {"create"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createProduct(@Valid @RequestBody ProductWS productWS, BindingResult bindingResult);

	@RequestMapping(value = {"read"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readProduct(@RequestParam Integer productId);
}
