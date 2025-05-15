package com.example.application.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleOriginHelper {
	
	private Long refId;
	
	private double unitPrice;
	
	private int quantity;
	
	public double getAmount() {
		return quantity * unitPrice;
		
	}
	
}
