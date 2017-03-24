package com.jft.market.controllers;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jft.market.api.ProductApi;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.model.Product;
import com.jft.market.model.ProductWS;
import com.jft.market.service.ProductService;

@Slf4j
@Controller
public class ProductController implements ProductApi {

	/*@Autowired
	private ProductRepository productRepository;*/

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity createProduct(@Valid @RequestBody ProductWS productWS, BindingResult bindingResult) {
		log.info("Validating paylaod");
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		log.info("Populating data");
		Product product = productService.populateData(productWS);
		productService.createProduct(product);
		return new ResponseEntity(productWS, HttpStatus.OK);
	}

	@Override
	public ResponseEntity readProduct(@RequestParam Integer productId) {
		return null;
	}

}
