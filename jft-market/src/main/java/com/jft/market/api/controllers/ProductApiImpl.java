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

import com.jft.market.api.ProductBean;
import com.jft.market.api.ProductBeanAttribute;
import com.jft.market.api.ws.EmberResponse;
import com.jft.market.api.ws.ProductWS;
import com.jft.market.api.ws.ResponseStatus;
import com.jft.market.exceptions.EntityNotFoundException;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.model.Product;
import com.jft.market.service.ProductService;

@Slf4j
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
		log.info("Populating data");
		Product product = productService.convertWStoEntity(productWS);
		productService.createProduct(product);
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
		List<ProductBean> productBeanList = productService.readProducts();
		List<ProductBeanAttribute> productBeanAttributes = new ArrayList<>();
		productBeanList.forEach(p -> productBeanAttributes.add(new ProductBeanAttribute(p.getId(), p)));
		if (productBeanList.isEmpty()) {
			throw new EntityNotFoundException("No product found");
		} else {
			return new ResponseEntity(new EmberResponse(productBeanAttributes), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity deleteProduct(@PathVariable("productUuid") String productUuid) {
		productService.deleteProduct(productUuid);
		return ResponseStatus.SUCCESS.getResponse();
	}
}
