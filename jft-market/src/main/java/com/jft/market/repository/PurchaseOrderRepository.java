package com.jft.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
