package com.jft.market.service;

import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.jft.market.api.CreditCardTypes;
import com.jft.market.model.PaymentInstrument;
import com.jft.market.model.Product;
import com.litle.sdk.LitleOnline;
import com.litle.sdk.generate.CardType;
import com.litle.sdk.generate.MethodOfPaymentTypeEnum;
import com.litle.sdk.generate.OrderSourceType;
import com.litle.sdk.generate.Sale;
import com.litle.sdk.generate.SaleResponse;

@Service("vantivService")
public class VantivServiceImpl {

	private LitleOnline litleOnline;

	public VantivServiceImpl() throws Exception {
		Resource resource = new ClassPathResource("/configuration.properties");
		Properties properties = PropertiesLoaderUtils.loadProperties(resource);
		this.litleOnline = new LitleOnline(properties);
	}

	public SaleResponse createSale(PaymentInstrument paymentInstrument, Product product) {
		Sale sale = new Sale();
		sale.setOrderId(product.getUuid().substring(0, 9));
		sale.setAmount(product.getPrice());
		sale.setOrderSource(OrderSourceType.ECOMMERCE);
		sale.setCard(buildCard(paymentInstrument));
		sale.setReportGroup("jft-market");
		return litleOnline.sale(sale);
	}

	public CardType buildCard(PaymentInstrument paymentInstrument) {
		CardType cardType = new CardType();
		cardType.setNumber(String.valueOf(paymentInstrument.getCreditCartNumber()));
		cardType.setExpDate(String.valueOf(paymentInstrument.getExpirationYear()));
		cardType.setType(convertVantivSpecificCreditCard(paymentInstrument.getType()));
		return cardType;
	}

	public static MethodOfPaymentTypeEnum convertVantivSpecificCreditCard(CreditCardTypes creditCardTypes) {
		switch (creditCardTypes) {
			case MASTER:
				return MethodOfPaymentTypeEnum.MC;
			case VISA:
				return MethodOfPaymentTypeEnum.VI;
			default:
				return null;
		}

	}
}
