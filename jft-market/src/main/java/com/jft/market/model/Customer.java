package com.jft.market.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends TimestampedFieldObject {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column(unique = true)
	private String email;
	private String uuid;

	@OneToOne(cascade = {javax.persistence.CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	Set<PaymentInstrument> paymentInstrumentList = new HashSet<PaymentInstrument>();

	@OneToMany(mappedBy = "customer")
	private Set<PurchaseOrder> purchaseOrders = new HashSet<>();

	@OneToMany(mappedBy = "customer")
	private Set<OrderCart> orderCarts = new HashSet<>();
}
