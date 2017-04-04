package com.jft.market.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public Set<Order> findByCustomer(Long customerId);
}
