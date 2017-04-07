package com.jft.market.api.controllers;

import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.api.ws.ProductWS;
import com.jft.market.api.ws.ResponseStatus;

import com.jft.market.exceptions.EntityNotFoundException;

import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.service.ProductService;

@Slf4j
@CrossOrigin
@RestController
public class ProductApiImpl implements ProductApi {

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity createProduct(@Valid @RequestBody ProductWS productWS, BindingResult bindingResult) {
		log.info("Validating paylaod");
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}

		productService.createProduct(productWS);
		return ResponseStatus.SUCCESS.getResponse();
	}

	@Override
	public ResponseEntity readProduct(@PathVariable("productUuid") String productUuid) {
		log.info("Reading product from database");
		ProductWS productWS = productService.readProduct(productUuid);
		return new ResponseEntity(productWS, HttpStatus.OK);
	}


	@Override
	@CrossOrigin(origins = "*")
	public ResponseEntity readProducts() {
		log.info("Reading products");
		List<ProductWS> products = productService.readProducts();
	/*	List<ProductBeanAttribute> productBeanAttributes = new ArrayList<>();
		System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnn");
		productBeanList.forEach(p -> productBeanAttributes.add(new ProductBeanAttribute(p.getId(), p)));
		if (productBeanList.isEmpty()) {
			throw new EntityNotFoundException("No product found");
		} else {

			//return new ResponseEntity(new EmberResponse(productBeanAttributes), HttpStatus.OK);
		}*/
		return new ResponseEntity(products, HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteProduct(@PathVariable("productUuid") String productUuid) {
		productService.deleteProduct(productUuid);
		return ResponseStatus.SUCCESS.getResponse();

	}

	@Override
	public ResponseEntity readProductByCategory(@PathVariable("categoryName") String categoryName) {
		List<ProductWS> products = productService.readProductsByCategoryName(categoryName);
		return new ResponseEntity(products, HttpStatus.OK);
	}
}
