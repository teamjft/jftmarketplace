package com.jft.market.api.controllers;

import java.util.ArrayList;
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

import com.jft.market.api.ApiConstants;
import com.jft.market.api.BeanAttribute;
import com.jft.market.api.ws.EmberResponse;
import com.jft.market.api.ws.ProductWS;
import com.jft.market.api.ws.SuccessWS;
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
		BeanAttribute productBeanAttribute = new BeanAttribute(ApiConstants.getSucessId(), new SuccessWS("success"), ApiConstants.PRODUCT);
		return new ResponseEntity(new EmberResponse<>(productBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readProduct(@PathVariable("productUuid") String productUuid) {
		log.info("Reading product from database");
		ProductWS productWS = productService.readProduct(productUuid);
		BeanAttribute productBeanAttribute = new BeanAttribute(productWS.getUuid(), productWS, ApiConstants.PRODUCTS);
		return new ResponseEntity(new EmberResponse<>(productBeanAttribute), HttpStatus.OK);
	}


	@Override
	public ResponseEntity readProducts() {
		log.info("Reading products");
		List<ProductWS> products = productService.readProducts();
		List<BeanAttribute> productBeanAttributes = new ArrayList<>();
		products.forEach(productWS -> {
			BeanAttribute productBeanAttribute = new BeanAttribute(productWS.getUuid(), productWS, ApiConstants.PRODUCTS);
			productBeanAttributes.add(productBeanAttribute);
		});
		return new ResponseEntity(new EmberResponse<>(productBeanAttributes), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteProduct(@PathVariable("productUuid") String productUuid) {
		productService.deleteProduct(productUuid);
		BeanAttribute productBeanAttribute = new BeanAttribute(productUuid, new SuccessWS("success"), ApiConstants.PRODUCT);
		return new ResponseEntity(new EmberResponse<>(productBeanAttribute), HttpStatus.OK);

	}

	@Override
	public ResponseEntity readProductByCategory(@PathVariable("categoryName") String categoryName) {
		List<ProductWS> products = productService.readProductsByCategoryName(categoryName);
		List<BeanAttribute> productBeanAttributes = new ArrayList<>();
		products.forEach(productWS -> {
			BeanAttribute productBeanAttribute = new BeanAttribute(productWS.getUuid(), productWS, ApiConstants.PRODUCTS);
			productBeanAttributes.add(productBeanAttribute);
		});
		return new ResponseEntity(new EmberResponse<>(productBeanAttributes), HttpStatus.OK);

	}

	@Override
	public ResponseEntity updateProduct(@Valid @RequestBody ProductWS productWS, @PathVariable("productUuid") String productUuid, BindingResult bindingResult) {
		log.info("Validating paylaod");
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		productService.updateProduct(productWS, productUuid);
		BeanAttribute productBeanAttribute = new BeanAttribute(productUuid, new SuccessWS("success"), ApiConstants.PRODUCT);
		return new ResponseEntity(new EmberResponse<>(productBeanAttribute), HttpStatus.OK);
	}
}
