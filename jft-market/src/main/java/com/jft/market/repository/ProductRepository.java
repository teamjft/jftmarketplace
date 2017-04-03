package com.jft.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Product findByName(String productName);

	public Product findByUuid(String productUuid);
}
