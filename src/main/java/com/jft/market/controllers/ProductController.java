package com.jft.market.controllers;

import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jft.market.api.ProductApi;
import com.jft.market.api.ProductBean;
import com.jft.market.api.ws.ProductWS;
import com.jft.market.exceptions.EntityNotFoundException;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.model.Product;
import com.jft.market.service.ProductService;

@Slf4j
@Controller
public class ProductController implements ProductApi {

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
		return new ResponseEntity(productWS, HttpStatus.OK);
	}

	@Override
	public ResponseEntity readProduct(@PathVariable Integer productId) {
		log.info("Reading product from database");
		ProductBean productBean = productService.readProduct(productId);
		if (productBean == null) {
			throw new EntityNotFoundException("Entity not found with id : " + productId);
		} else {
			return new ResponseEntity(productBean, HttpStatus.OK);
		}
	}


	@Override
	@CrossOrigin(origins = "*")
	public ResponseEntity readProducts() {
		log.info("Reading products");
		List<ProductBean> productBeanList = productService.readProducts();
		if (productBeanList.isEmpty()) {
			throw new EntityNotFoundException("No product found");
		} else {
			return new ResponseEntity(productBeanList, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity deleteProduct(@PathVariable("productId") Integer productId) {
		log.info("Reading product from database");
		ProductBean productBean = productService.readProduct(productId);
		if (productBean == null) {
			throw new EntityNotFoundException("Entity not found. Please provide valid entity to delete");
		}
		log.info("Deleting product");
		productService.deleteProduct(productBean);
		return new ResponseEntity(HttpStatus.OK);
	}
}
