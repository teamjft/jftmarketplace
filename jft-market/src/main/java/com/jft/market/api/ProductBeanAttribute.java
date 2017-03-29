package com.jft.market.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductBeanAttribute {

	private String type = "products";
	private Integer id;
	private ProductBean attributes;

	public ProductBeanAttribute(Integer id, ProductBean productBean) {
		this.id = id;
		this.attributes = productBean;
	}
}
