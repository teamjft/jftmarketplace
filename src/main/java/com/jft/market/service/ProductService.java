package com.jft.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jft.market.model.Product;
import com.jft.market.model.ProductWS;
import com.jft.market.repository.ProductRepository;

@Service("productService")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product populateData(ProductWS productWS) {
		Product product = new Product();
		product.setName(productWS.getName());
		product.setDescription(productWS.getDescription());
		product.setFeatures(productWS.getFeatures());
		product.setPrice(productWS.getPrice());
		return product;
	}

	public void createProduct(Product product) {
		productRepository.createProduct(product);
	}

	public void readProduct() {
/*		productRepositor*/
	}
}
