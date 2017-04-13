package com.jft.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.ws.CategoryWS;
import com.jft.market.api.ws.ProductWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Category;
import com.jft.market.model.Product;
import com.jft.market.repository.CategoryRepository;
import com.jft.market.repository.ProductRepository;
import com.jft.market.util.Preconditions;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional
	public ProductWS readProduct(String productUuid) {
		Product product = readProductByUuid(productUuid);
		Preconditions.check(product.getDeleted().equals(Boolean.TRUE), ExceptionConstants.PRODUCT_IS_DELETED);
		return convertEntityToWS(product);
	}

	@Override
	@Transactional
	public List<ProductWS> readProducts() {
		List<Product> productList = productRepository.findAll();
		List<ProductWS> productWSList = new ArrayList<>();
		productList.forEach(product -> {
			if (product.getDeleted().equals(Boolean.FALSE)) {
				ProductWS productWS = convertEntityToWS(product);
				productWSList.add(productWS);
			}
		});
		return productWSList;
	}

	@Override
	@Transactional
	public void saveProduct(Product product, Category category) {
		if (StringUtils.isEmpty(product.getUuid())) {
			product.setUuid(UUID.randomUUID().toString());
		}
		if (StringUtils.isEmpty(category.getUuid())) {
			category.setUuid(UUID.randomUUID().toString());
		}
		categoryRepository.save(category);
		productRepository.save(product);

	}

	@Override
	@Transactional
	public void deleteProduct(String productUuid) {
		Product product = productRepository.findByUuid(productUuid);
		Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND_TO_DELETE);
		product.setDeleted(Boolean.TRUE);
		productRepository.save(product);
	}

	@Override
	public Product convertWStoEntity(ProductWS productWS, Product product1) {
		if (product1 == null) {
			Product product = new Product();
			product.setName(productWS.getName());
			product.setDescription(productWS.getDescription());
			product.setFeatures(productWS.getFeatures());
			product.setPrice(productWS.getPrice());
			return product;
		} else {
			product1.setName(productWS.getName());
			product1.setDescription(productWS.getDescription());
			product1.setFeatures(productWS.getFeatures());
			product1.setPrice(productWS.getPrice());
			return product1;
		}
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
		product.getCategories().forEach(category -> {
			CategoryWS categoryWS = new CategoryWS();
			categoryWS.setName(category.getName());
			categoryWS.setDescription(category.getDescription());
			categoryWS.setUuid(category.getUuid());
			productWS.getCategories().add(categoryWS);
		});
		return productWS;
	}

	@Override
	@Transactional
	public void createProduct(ProductWS productWS) {
		Product product = convertWStoEntity(productWS, null);
		checkIfProductExist(product);
		List<CategoryWS> categoryWSList = productWS.getCategories();
		List<String> categoryNamesFromWS = new ArrayList<>();
		categoryWSList.forEach(categoryWS -> {
			categoryNamesFromWS.add(categoryWS.getName());
		});
		List<Category> categoryListFromDB = categoryRepository.findByNameIn(categoryNamesFromWS);
		Preconditions.check((categoryListFromDB.size() != categoryNamesFromWS.size()), ExceptionConstants.PLEASE_CREATE_CATEGORY);
		categoryListFromDB.forEach(category -> {
			product.getCategories().add(category);
			category.getProducts().add(product);
			saveProduct(product, category);
		});
	}

	@Override
	public void checkIfProductExist(Product product) {
		Product product1 = productRepository.findByName(product.getName());
		Preconditions.check(product1 != null, ExceptionConstants.PRODUCT_ALREADY_EXIST);
	}

	@Override
	@Transactional
	public List<ProductWS> readProductsByCategoryName(String name) {
		List<Product> products = productRepository.findAll(
				Specifications.where(((root, criteriaQuery, criteriaBuilder) -> {
					Join<Product, Category> categories = root.join("categories", JoinType.INNER);
					return criteriaBuilder.and(criteriaBuilder.equal(categories.get("name"), name),
							criteriaBuilder.equal(categories.get("deleted"), Boolean.FALSE));
				}))
		);
		return convertProductsListToWSList(products);
	}

	@Override
	public List<ProductWS> convertProductsListToWSList(List<Product> products) {
		List<ProductWS> productWSList = new ArrayList<>();
		products.forEach(product -> {
			ProductWS productWS = convertEntityToWS(product);
			productWSList.add(productWS);
		});
		return productWSList;
	}

	@Override
	@Transactional
	public void updateProduct(ProductWS productWS, String uuid) {
		Product product = readProductByUuid(uuid);
		Preconditions.check(product == null, ExceptionConstants.PRODUCT_NOT_FOUND);
		Preconditions.check(!isProductEnabled(product), ExceptionConstants.PRODUCT_IS_DELETED);
		updateAndSaveProduct(product, productWS);
	}

	@Override
	@Transactional
	public Product readProductByUuid(String uuid) {
		return productRepository.findByUuid(uuid);
	}

	@Override
	public Boolean isProductEnabled(Product product) {
		return (product.getEnabled().equals(Boolean.TRUE) && product.getDeleted().equals(Boolean.FALSE)) ? true : false;
	}

	@Override
	@Transactional
	public void updateAndSaveProduct(Product product, ProductWS productWS) {
		product.setName(productWS.getName());
		product.setDescription(productWS.getDescription());
		product.setFeatures(productWS.getFeatures());
		product.setPrice(productWS.getPrice());
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		if (StringUtils.isEmpty(product.getUuid())) {
			product.setUuid(UUID.randomUUID().toString());
		}
		productRepository.save(product);
	}
}

