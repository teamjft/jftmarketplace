package com.jft.market.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer price;
	private String description;
	private String features;
	private String uuid;

	@ManyToOne
	private Customer customer;

	public Product(String name, Integer price, String description, String features) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.features = features;
	}
}
