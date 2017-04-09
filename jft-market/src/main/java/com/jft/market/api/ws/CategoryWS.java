package com.jft.market.api.ws;


import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jft.market.model.Product;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryWS {

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Product> products;

	@NotNull
	private String name;
	@NotNull
	private String description;

	private String uuid;
}
