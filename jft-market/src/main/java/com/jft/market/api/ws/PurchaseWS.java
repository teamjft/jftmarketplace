package com.jft.market.api.ws;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "purchase")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseWS {

	@NotNull
	private String customerUuid;
	@NotNull
	private String customerEmail;
	@NotNull
	private String productUuid;
	@NotNull
	private String productName;

}
