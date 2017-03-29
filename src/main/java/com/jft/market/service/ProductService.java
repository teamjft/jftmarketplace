package com.jft.market.service;

import java.util.List;

import com.jft.market.api.ProductBean;
import com.jft.market.api.ws.ProductWS;
import com.jft.market.exceptions.EntityNotFoundException;
import com.jft.market.model.Product;

/**
 * Created by gagan on 29/3/17.
 */
public interface ProductService {
	ProductBean readProduct(Integer productId) throws EntityNotFoundException;

	List<ProductBean> readProducts();

	void createProduct(Product product);

	void deleteProduct(ProductBean productBean);

	Product convertWStoEntity(ProductWS productWS);

	Product convertBeanToEntity(ProductBean productBean);

	ProductBean createProductBean(Product product);
}
