package com.jft.market.repository;


import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jft.market.model.Product;

@Repository("productRepository")
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private Session session;

	@Override
	public void createProduct(Product product) {
		log.info("Into create product method");
		Transaction transaction = session.beginTransaction();
		session.save(product);
		transaction.commit();
		session.close();
	}

	@Override
	public void updateProduct(Integer productId) {

	}

	@Override
	public void deleteProductById(Integer productId) {

	}

	@Override
	public void findAllProducts() {

	}

	@Override
	public void findProductById(Integer productId) {

	}
}
