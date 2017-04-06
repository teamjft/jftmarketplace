package com.jft.market.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.PurchaseOrder;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Long> {

	public Set<PurchaseOrder> findByCustomer(Long customerId);
}
