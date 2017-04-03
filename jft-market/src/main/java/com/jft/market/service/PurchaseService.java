package com.jft.market.service;


import com.jft.market.model.Customer;
import com.jft.market.model.Product;

public interface PurchaseService {

	public void purchaseProduct(String customerUuid, String productUuid);

	public Customer getCustomer(String customerUuid);

	public Product getProduct(String productUuid);

	public void associateCustomerWithProduct(Product product, Customer customer);

	//public void linkProductWithCustomer(Product product);

	/*public Long getProductId(ProductWS productWS);

	public Long getUserId(UserWS userWS);

	public UserWS createUserWS(PurchaseWS purchaseWS);

	public ProductWS createProductWS(PurchaseWS purchaseWS);

	public void makePurchase(Long userid, Long productid);

	public Long getCustomerId(UserWS userWS);

	public Customer getCustomer(UserWS userWS);*/
}
