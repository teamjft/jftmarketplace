package com.jft.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.ProductBean;
import com.jft.market.api.ws.ProductWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Product;
import com.jft.market.repository.ProductRepository;
import com.jft.market.util.Preconditions;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductWS readProduct(String productUuid) {
		Product product = productRepository.findByUuid(productUuid);
		return convertEntityToWS(product);
	}

	@Override
	public List<ProductBean> readProducts() {
		List<Product> productList = productRepository.findAll();
		List<ProductBean> productBeanList = new ArrayList<ProductBean>();
		productList.forEach(product -> {
			ProductBean productBean = createProductBean(product);
			productBeanList.add(productBean);
		});
		return productBeanList;
	}

	@Override
	public void createProduct(Product product) {
		if (StringUtils.isEmpty(product.getUuid())) {
			product.setUuid(UUID.randomUUID().toString());
		}
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void deleteProduct(String productUuid) {
		Product product = productRepository.findByUuid(productUuid);
		Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND_TO_DELETE);
		productRepository.delete(product);
	}

	@Override
	public Product convertWStoEntity(ProductWS productWS) {
		Product product = new Product();
		product.setName(productWS.getName());
		product.setDescription(productWS.getDescription());
		product.setFeatures(productWS.getFeatures());
		product.setPrice(productWS.getPrice());
		return product;
	}

	@Override
	public ProductBean createProductBean(Product product) {
		Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND);
		ProductBean productBean = new ProductBean();
		productBean.setId(product.getId());
		productBean.setName(product.getName());
		productBean.setPrice(product.getPrice());
		productBean.setDescription(product.getDescription());
		productBean.setFeatures(product.getFeatures());
		return productBean;
	}

	@Override
	public ProductWS convertEntityToWS(Product product) {
		Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND);
		ProductWS productWS = new ProductWS();
		productWS.setName(product.getName());
		productWS.setUuid(product.getUuid());
		productWS.setDescription(product.getDescription());
		productWS.setFeatures(product.getFeatures());
		productWS.setPrice(product.getPrice());
		return productWS;
	}
}
