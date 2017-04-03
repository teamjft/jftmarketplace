package com.jft.market.service;

import java.util.List;

import com.jft.market.api.ProductBean;
import com.jft.market.api.ws.ProductWS;
import com.jft.market.model.Product;

public interface ProductService {

	public ProductWS readProduct(String productUuid);

	public List<ProductBean> readProducts();

	public void createProduct(Product product);

	public void deleteProduct(String productUuid);

	public Product convertWStoEntity(ProductWS productWS);

	public Product convertBeanToEntity(ProductBean productBean);

	public ProductBean createProductBean(Product product);

	public ProductWS convertEntityToWS(Product product);
}
