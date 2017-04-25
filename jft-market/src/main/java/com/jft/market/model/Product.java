package com.jft.market.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product extends TimestampedFieldObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Long price;
	private String description;
	private String features;
	private String uuid;

	public Product(String name, Long price, String description, String features) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.features = features;
	}
	@ManyToMany
	@JoinTable(
			name = "product_category",
			joinColumns = {@JoinColumn(name = "product_id")},
			inverseJoinColumns = {@JoinColumn(name = "category_id")}
	)
	private Set<Category> categories = new HashSet<>();

	@ManyToMany
	private Set<OrderCart> orderCarts = new HashSet<>();
}
