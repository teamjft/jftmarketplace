package com.jft.market.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jft.market.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	public Product findByName(String productName);

	public Product findByUuid(String productUuid);

	public List<Product> findByNameIn(Collection<String> nameList);
}
