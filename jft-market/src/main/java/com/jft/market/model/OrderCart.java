package com.jft.market.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jft.market.api.OrderCartStatus;

@Entity
@Table(name = "order_cart")
@NoArgsConstructor
@Getter
@Setter
public class OrderCart extends TimestampedFieldObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String uuid;
	@Enumerated(EnumType.STRING)
	private OrderCartStatus status;

	@ManyToMany
	@JoinTable(
			name = "ordercart_product",
			joinColumns = {@JoinColumn(name = "order_cart_id")},
			inverseJoinColumns = {@JoinColumn(name = "product_id")}
	)
	private Set<Product> products = new HashSet<>();

	@ManyToOne
	private Customer customer;

	@OneToOne(mappedBy = "orderCart")
	private PurchaseOrder purchaseOrder;
}
