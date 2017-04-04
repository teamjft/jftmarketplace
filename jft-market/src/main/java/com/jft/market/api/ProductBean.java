package com.jft.market.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductBean {

	private Long id;
	private String name;
	private Long price;
	private String description;
	private String features;

	public ProductBean(Long id, String name, Long price, String description, String features) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.features = features;
	}
}
