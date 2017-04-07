package com.jft.market.api;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jft.market.model.Category;

@NoArgsConstructor
@Getter
@Setter
public class ProductBean {

	private Long id;
	private String name;
	private Long price;
	private String description;
	private String features;
	private Set<Category> categories = new HashSet<>();

	public ProductBean(Long id, String name, Long price, String description, String features) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.features = features;
	}
}
