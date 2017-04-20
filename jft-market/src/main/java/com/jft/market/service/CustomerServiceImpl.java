package com.jft.market.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.ws.CustomerWS;
import com.jft.market.api.ws.UserWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Customer;
import com.jft.market.model.User;
import com.jft.market.repository.CustomerRepository;
import com.jft.market.repository.UserRepository;
import com.jft.market.util.Preconditions;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void convertWStoEntityAndSave(CustomerWS customerWS) {
		Customer customer = convertWStoEntity(customerWS);
		createCustomer(customer);
	}

	@Override
	public Customer convertWStoEntity(CustomerWS customerWS) {
		Preconditions.check(customerWS == null, ExceptionConstants.CUSTOMER_PAYLOAD_EMPTY);
		Customer customer = new Customer();
		customer.setEmail(customerWS.getEmail());
		UserWS userWS = createUserWSFromCustomerWS(customerWS);
		User user = userService.convertWStoEntity(userWS);
		if (StringUtils.isEmpty(user.getUuid())) {
			user.setUuid(UUID.randomUUID().toString());
		}
		customer.setUser(user);
		user.setCustomer(customer);
		return customer;
	}

	@Override
	public void createCustomer(Customer customer) {
		if (StringUtils.isEmpty(customer.getUuid())) {
			customer.setUuid(UUID.randomUUID().toString());
		}
		customerRepository.save(customer);
	}

	@Override
	public UserWS createUserWSFromCustomerWS(CustomerWS customerWS) {
		if (customerWS != null) {
			UserWS userWS = new UserWS();
			Boolean isUserAlreadyExist = userService.checkIfUserAlreadyExist(customerWS.getEmail());
			Preconditions.check(isUserAlreadyExist, ExceptionConstants.USER_ALREADY_EXIST);
			userWS.setFname(customerWS.getFname());
			userWS.setLname(customerWS.getLname());
			userWS.setPhone(customerWS.getPhone());
			userWS.setPassword(customerWS.getPassword());
			userWS.setEmail(customerWS.getEmail());
			userWS.setGender(customerWS.getGender());
			userWS.setUuid(UUID.randomUUID().toString());
			return userWS;
		}
		return null;
	}

	@Override
	@Transactional
	public Customer readCustomerByEmailId(CustomerWS customerWS) {
		Customer customer = customerRepository.findByEmail(customerWS.getEmail());
		return customer;
	}

	@Override
	@Transactional
	public Customer readCustomerByUuid(String customerUuid) {
		Customer customer = customerRepository.findByUuid(customerUuid);
		Preconditions.check(customer == null, ExceptionConstants.CUSTOMER_NOT_FOUND);
		Preconditions.check(!isValidCustomer(customer), ExceptionConstants.CUSTOMER_NOT_ENABLED);
		return customer;
	}

	@Override
	public CustomerWS convertEntityToWS(Customer customer) {
		System.out.println(" ============ SecurityContextHolder.getContext().getAuthentication().getPrincipal() ============= " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Preconditions.check(customer == null, ExceptionConstants.CUSTOMER_NOT_FOUND);
		Preconditions.check(!isValidCustomer(customer), ExceptionConstants.CUSTOMER_NOT_ENABLED);
		CustomerWS customerWS = new CustomerWS();
		customerWS.setFname(customer.getUser().getFname());
		customerWS.setLname(customer.getUser().getLname());
		customerWS.setEmail(customer.getEmail());
		customerWS.setUuid(customer.getUuid());
		customerWS.setGender(customer.getUser().getGender());
		customerWS.setPhone(customer.getUser().getPhone());
		return customerWS;

	}

	@Override
	@Transactional
	public List<CustomerWS> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		List<Customer> enabledCustomers = new ArrayList<Customer>();
		customers.forEach(customer -> {
			if (isValidCustomer(customer)) {
				enabledCustomers.add(customer);
			}
		});
		return convertEntityListToWSList(enabledCustomers);

	}

	@Override
	public List<CustomerWS> convertEntityListToWSList(List<Customer> customers) {
		Preconditions.check(customers.isEmpty(), ExceptionConstants.CUSTOMER_NOT_FOUND);
		List<CustomerWS> customerWSList = new ArrayList<CustomerWS>();
		customers.forEach(customer -> {
			CustomerWS customerWS = convertEntityToWS(customer);
			customerWSList.add(customerWS);
		});
		return customerWSList;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		User user = userService.findByEmail(customer.getEmail());
		if (user != null) {
			user.setEnabled(Boolean.FALSE);
			user.setDeleted(Boolean.TRUE);
			userRepository.save(user);
		}
		customer.setDeleted(Boolean.TRUE);
		customer.setEnabled(Boolean.FALSE);
		customerRepository.save(customer);
	}

	@Override
	public Boolean isValidCustomer(Customer customer) {
		return (customer.getEnabled().equals(Boolean.TRUE) && customer.getDeleted().equals(Boolean.FALSE)) ? true : false;
	}

	@Override
	@Transactional
	public void updateCustomer(CustomerWS customerWS, String uuid) {
		Customer enabledCustomer = readCustomerByUuid(uuid);
		User associatedUser = userRepository.findByemail(enabledCustomer.getEmail());
		Boolean isAssociatedUserEnabled = userService.isValidUser(associatedUser);
		Preconditions.check(!isAssociatedUserEnabled, ExceptionConstants.CUSTOMER_NOT_ENABLED);
		if (isAssociatedUserEnabled) {
			associatedUser.setFname(customerWS.getFname());
			associatedUser.setLname(customerWS.getLname());
			associatedUser.setPhone(customerWS.getPhone());
			userService.saveUser(associatedUser);
		}
	}

	@Override
	public void validateCustomerWS(CustomerWS customerWS) {
		Preconditions.check(StringUtils.isEmpty(customerWS.getEmail()), ExceptionConstants.EMAIL_CANNOT_BE_EMPTY);
		Preconditions.check(StringUtils.isEmpty(customerWS.getFname()), ExceptionConstants.FIRST_NAME_CANNOT_BE_EMPTY);
		Preconditions.check(StringUtils.isEmpty(customerWS.getLname()), ExceptionConstants.LAST_NAME_CANNOT_BE_EMPTY);
		Preconditions.check(StringUtils.isEmpty(String.valueOf(customerWS.getPhone())), ExceptionConstants.PHONE_NUMBER_CANNOT_BE_EMPTY);
	}

	@Override
	@Transactional
	public UserWS readUpdatedCustmerWS(String uuid) {
		Preconditions.check(uuid == null, ExceptionConstants.CUSTOMER_NOT_FOUND);
		String userEmail = customerRepository.findByUuid(uuid).getUser().getEmail();
		User updatedUser = userService.findByEmail(userEmail);
		return userService.convertEntityToWS(updatedUser);
	}
}
