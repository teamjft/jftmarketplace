package com.jft.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.model.Customer;
import com.jft.market.model.Product;
import com.jft.market.repository.CustomerRepository;
import com.jft.market.repository.ProductRepository;
import com.jft.market.util.Preconditions;

@Service("purchaseService")
public class PurchaseServiceimpl implements PurchaseService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public void purchaseProduct(String customerUuid, String productUuid) {
		Customer customer = getCustomer(customerUuid);
		Product product = getProduct(productUuid);
		Preconditions.check(product == null, "Product Not found");
		associateCustomerWithProduct(product, customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(String customerUuid) {
		Customer customer = customerRepository.findByUuid(customerUuid);
		//System.out.println("customer  = " + customer.getEmail());
		Preconditions.check(customer == null, "Customer not found");
		return customer;
	}

	@Override
	public Product getProduct(String productUuid) {
		Product product = productRepository.findByUuid(productUuid);
		Preconditions.check(product == null, "Product not exist");
		return product;
	}

	@Override
	public void associateCustomerWithProduct(Product product, Customer customer) {
		Preconditions.check(product.getCustomer() != null, "Product already purchased. You cannot buy this product");
		product.setCustomer(customer);
		product.getCustomer().setId(customer.getId());
		productRepository.save(product);
	}
/*
	@Override
	public void linkProductWithCustomer(Product product) {

	}*/
/*
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private CustomerRepository customerRepository;


	@Override
	@Transactional
	public Long getProductId(ProductWS productWS) {
		Product product = productRepository.findByName(productWS.getName());
		return product != null ? product.getId() : null;
	}

	@Override
	@Transactional
	public Long getUserId(UserWS userWS) {
		User user = userRepository.findByusername(userWS.getUsername());
		return user != null ? user.getId() : null;
	}

	@Override
	public UserWS createUserWS(PurchaseWS purchaseWS) {
		UserWS userWS = new UserWS();
		userWS.setUsername(purchaseWS.getUsername());
		userWS.setEmail(purchaseWS.getEmail());
		userWS.setUuid(purchaseWS.getUserUuid());
		return userWS;
	}

	@Override
	public ProductWS createProductWS(PurchaseWS purchaseWS) {
		ProductWS productWS = new ProductWS();
		productWS.setName(purchaseWS.getProduct().getName());
		return productWS;
	}

	@Override
	@Transactional
	public void makePurchase(Long customerId, Long productid) {
		ProductEntitlement productEntitlement = new ProductEntitlement();
		productEntitlement.setUserId(customerId);
		productEntitlement.setProductId(productid);
		if (StringUtils.isNotEmpty(productEntitlement.getUuid())) {
			productEntitlement.setUuid(UUID.randomUUID().toString());
		}
		purchaseRepository.save(productEntitlement);
	}


	@Override
	public Long getCustomerId(UserWS userWS) {
		Customer customer = null; // customerRepository.findByUserUUID(userWS.getUuid());
		return customer != null ? customer.getId() : null;
	}

	@Override
	public Customer getCustomer(UserWS userWS) {
		Customer customer = null;//customerRepository.findByUserUUID(userWS.getUuid());
		return customer != null ? customer : null;
	}*/

}
