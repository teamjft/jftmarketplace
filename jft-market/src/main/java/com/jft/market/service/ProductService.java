package com.jft.market.service;

import java.util.List;

import com.jft.market.api.ws.ProductWS;
import com.jft.market.model.Category;
import com.jft.market.model.Product;

public interface ProductService {

	public ProductWS readProduct(String productUuid);

	public List<ProductWS> readProducts();

	public void saveProduct(Product product, Category category);

	public void deleteProduct(String productUuid);

	public Product convertWStoEntity(ProductWS productWS, Product product);

	public ProductWS convertEntityToWS(Product product);

	public void createProduct(ProductWS productWS);

	public void checkIfProductExist(Product product);

	public List<ProductWS> readProductsByCategoryName(String name);

	public List<ProductWS> convertProductsListToWSList(List<Product> products);

	public void updateProduct(ProductWS productWS, String uuid);

	public Product readProductByUuid(String uuid);

	public Boolean isProductEnabled(Product product);

	public void updateAndSaveProduct(Product product, ProductWS productWS);

	public void saveProduct(Product product);
}
