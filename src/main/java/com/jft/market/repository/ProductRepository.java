package com.jft.market.repository;

import com.jft.market.model.Product;


public interface ProductRepository {
	public void createProduct(Product product);

	public void updateProduct(Integer productId);

	public void deleteProductById(Integer productId);

	public void findAllProducts();

	public void findProductById(Integer productId);
}
