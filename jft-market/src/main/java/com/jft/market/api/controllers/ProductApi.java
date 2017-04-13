package com.jft.market.api.controllers;


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

@RequestMapping(value = BaseApi.BASE_PATH + ProductApi.PRODUCT)
public interface ProductApi extends BaseApi {

	String PRODUCT = "v1/product";
	String PRODUCTS = "products";

	@RequestMapping(value = {"create"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createProduct(@Valid @RequestBody ProductWS productWS, BindingResult bindingResult);

	@RequestMapping(value = {"{productUuid}"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readProduct(@PathVariable("productUuid") String productUuid);

	@RequestMapping(value = {PRODUCTS},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readProducts();

	@RequestMapping(value = {"delete/{productUuid}"},
			method = RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity deleteProduct(@PathVariable("productUuid") String productUuid);


	@RequestMapping(value = {"/category/{categoryName}"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readProductByCategory(@PathVariable("categoryName") String categoryName);

	@RequestMapping(value = {"update/{productUuid}"},
			method = RequestMethod.PATCH,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity updateProduct(@RequestBody ProductWS productWS,
								 @PathVariable("productUuid") String productUuid, BindingResult bindingResult);
}
