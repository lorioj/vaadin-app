package com.example.application.helper;

import java.util.HashMap;
import java.util.Map;

import com.example.application.model.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaleHelper {

	private Long refId;

	private Product product;

	private Map<String, SaleOriginHelper> saleOrigin;

	public SaleHelper() {
		saleOrigin = new HashMap<>();
	}

	public void setSaleOrigin(String key, SaleOriginHelper origin) {
		saleOrigin.put(key, origin);
	}

	public double getAmountByOrigin(String origin) {
		if (saleOrigin.containsKey(origin)) {
			return saleOrigin.get(origin).getAmount();
		}
		return 0;
	}

	public void setUnitpriceByOrigin(String origin, double price) {
		if (saleOrigin.containsKey(origin)) {
			saleOrigin.get(origin).setUnitPrice(price);
		}
	}

	public void setQuantityByOrigin(String origin, int qnty) {
		if (saleOrigin.containsKey(origin)) {
			saleOrigin.get(origin).setQuantity(qnty);
		} else {
			System.err.println(origin + "is not exisit in the map");
		}
	}

}
