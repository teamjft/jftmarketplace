package com.jft.market.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jft.market.api.ProductBean;
import com.jft.market.api.ws.ProductWS;
import com.jft.market.exceptions.EntityNotFoundException;
import com.jft.market.model.Product;
import com.jft.market.repository.ProductRepository;

@Service("productService")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductBean readProduct(Integer productId) throws EntityNotFoundException {
		Product product = productRepository.findOne(productId);
		ProductBean productBean = createProductBean(product);
		return productBean;
	}

	public List<ProductBean> readProducts() {
		List<Product> productList = productRepository.findAll();
		List<ProductBean> productBeanList = new ArrayList<ProductBean>();
		productList.forEach(product -> {
			ProductBean productBean = createProductBean(product);
			productBeanList.add(productBean);
		});
		return productBeanList;
	}

	public void createProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(ProductBean productBean) {
		Product product = convertBeanToEntity(productBean);
		productRepository.delete(product);
	}

	public Product convertWStoEntity(ProductWS productWS) {
		Product product = new Product();
		product.setName(productWS.getName());
		product.setDescription(productWS.getDescription());
		product.setFeatures(productWS.getFeatures());
		product.setPrice(productWS.getPrice());
		return product;
	}

	public Product convertBeanToEntity(ProductBean productBean) {
		Product product = new Product();
		if (productBean != null) {
			product.setId(productBean.getId());
			product.setName(productBean.getName());
			product.setPrice(productBean.getPrice());
			product.setDescription(productBean.getDescription());
			product.setFeatures(productBean.getFeatures());

			return product;
		}
		return null;
	}

	public ProductBean createProductBean(Product product) {
		ProductBean productBean = new ProductBean();
		if (product != null) {
			productBean.setId(product.getId());
			productBean.setName(product.getName());
			productBean.setPrice(product.getPrice());
			productBean.setDescription(product.getDescription());
			productBean.setFeatures(product.getFeatures());
			return productBean;
		}
		return null;
	}
}
