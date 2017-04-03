package com.jft.market.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.PaymentInstrument;

public interface PaymentInstrumentRepository extends JpaRepository<PaymentInstrument, Long> {

	public List<PaymentInstrument> findByCustomerUuid(String customerUuid);

	public PaymentInstrument findByUuid(String paymentInstrumentUuid);
}
