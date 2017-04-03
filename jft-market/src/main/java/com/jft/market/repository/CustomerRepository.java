package com.jft.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByUuid(String customerUuid);

	public Customer findByEmail(String email);
}
