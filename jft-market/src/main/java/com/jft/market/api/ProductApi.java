package com.jft.market.api;


import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jft.market.api.ws.ProductWS;

@RequestMapping(value = ProductApiConstants.BASE_PATH)
public interface ProductApi {

	@RequestMapping(value = {"create"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createProduct(@Valid @RequestBody ProductWS productWS, BindingResult bindingResult);

	@RequestMapping(value = {"{productId}"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readProduct(@PathVariable("productId") Integer productId);

	@RequestMapping(value = {ProductApiConstants.PRODUCTS},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readProducts();

	@RequestMapping(value = {"delete/{productId}"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity deleteProduct(@PathVariable("productId") Integer productId);
}
