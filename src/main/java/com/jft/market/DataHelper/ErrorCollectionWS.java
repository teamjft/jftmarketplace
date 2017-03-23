package com.jft.market.DataHelper;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "errors")
public class ErrorCollectionWS {

	private List<ErrorWS> error = new ArrayList();

	public void add(ErrorWS error) {
		this.error.add(error);
	}
}
