package com.jft.market.exceptions;


public class ExceptionConstants {

	public static final String USER_NOT_FOUND = "User not found";
	public static final String USER_NOT_ENABLED = "User is not enabled or is deleted.";
	public static final String USER_ALREADY_EXIST = "User already exist";

	public static final String CUSTOMER_NOT_FOUND = "Customer not found";
	public static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";
	public static final String CUSTOMER_NOT_ENABLED = "Customer is not enabled or is deleted.";
	public static final String CUSTOMER_NOT_ASSOCIATED_WITH_ORDER_CART = "Customer not associated with order cart.";


	public static final String PRODUCT_NOT_FOUND = "Product not found";
	public static final String PRODUCT_ALREADY_PURCHASED = "Product not found";
	public static final String PRODUCT_ALREADY_EXIST = "Product already exists.";
	public static final String PRODUCT_NOT_FOUND_TO_DELETE = "Product not found. Please provide valid product Id to delete.";
	public static final String ORDER_CART_IS_EMPTY = "Order cart is empty";
	public static final String PRODUCT_IS_DELETED = "Product is deleted";

	public static final String PAYMENT_INSTRUMENT_NOT_FOUND = "Paymnet Instrument not found";

	public static final String PAYMENT_ERROR = "Payment not succeeded. Please try after sometime";
	public static final String PATMENT_GATEWAY_ERROR = "No response from payment gateway";

	public static final String INVALID_REQUEST = "Invalid request";


	public static final String PHONE_NUMBER_CANNOT_BE_EMPTY = "Phone number cannot be empty";
	public static final String EMAIL_CANNOT_BE_EMPTY = "Email cannot be empty";
	public static final String FIRST_NAME_CANNOT_BE_EMPTY = "First name cannot be empty";
	public static final String LAST_NAME_CANNOT_BE_EMPTY = "Last name cannot be empty";

	public static final String NO_ROLE_TO_SAVE = "No role to save";

	public static final String CATEGORY_NOT_FOUND = "Category not found";
	public static final String INVALID_USERNAME_PASSWORD = "Invalid username/password";
	public static final String CATEGORY_ALREADY_EXIST = "Category already exist";
	public static final String NO_CATEGORY_FOUND = "No category found";
	public static final String PLEASE_CREATE_CATEGORY = "No category exist with this name. Please create category first.";
	public static final String CATEGORY_IS_DELETED = "Category not found. It is deleted";
	public static final String CATEGORY_ALREADY_DELETED = "Category already deleted";

	public static final String CUSTOMER_PAYLOAD_EMPTY = "Customer payload is empty. Please provide valid payload.";

	public static final String CUSTOMER_ALREADY_HAS_ORDER_CART = "Customer already has order cart";

	public static final String NO_ORDER_CART_FOUND = "Order cart not found";
	public static final String PRODUCT_ALREADY_ADDED_INTO_CART = "Product already added into cart.";
	public static final String PRODUCT_NOT_ASSOCIATED_TO_CART = "Product not associated to this cart.";
	public static final String NO_ACTIVE_PAYMNET_INSTRUMENT_FOUND = "No active payment instrument found";


}
