package com.jft.market.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productEntitlement")
@Setter
@Getter
@NoArgsConstructor
public class ProductEntitlement extends TimestampedFieldObject {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String uuid;

	private Long userId;
	private Long productId;
	private String activePaymentinstrument;
}
