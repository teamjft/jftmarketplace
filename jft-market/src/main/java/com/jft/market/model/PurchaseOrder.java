package com.jft.market.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchase_orders")
@Setter
@Getter
@NoArgsConstructor
public class PurchaseOrder extends TimestampedFieldObject {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long Id;
	@Column(name = "order_status")
	private String orderStatus;
	private String uuid;

	@ManyToOne
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "order_cart_id")
	private OrderCart orderCart;
}
