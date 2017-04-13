package com.jft.market.api;


import java.math.BigDecimal;
import java.util.UUID;

public class ApiConstants {

	public static final BigDecimal SUCCESS_ID = BigDecimal.ONE;
	public static final String SUCCESS = "success";

	public static final String CATEGORIES = "categorylist";
	public static final String CATEGORY = "category";
	public static final String PRODUCTS = "productlist";
	public static final String PRODUCT = "product";
	public static final String CUSTOMERS = "customerlist";
	public static final String CUSTOMER = "customer";

	public static final String REGISTRATION = "registration";
	public static final String PAYMENT_INSTRUMENT = "paymentinstrument";
	public static final String ORDERCART = "ordercart";

	public static String getSucessId() {
		return UUID.randomUUID().toString();
	}
}
