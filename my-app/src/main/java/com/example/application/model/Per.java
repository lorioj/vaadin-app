package com.example.application.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.textfield.TextField;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Per {
	
	
	private String name;
	
	private Map<Integer, TextField> map;
	
	public Per(String name) {
		
		this.name = name;
		map = new HashMap<>();
		
		for(int i = 1; i <= LocalDate.now().lengthOfMonth(); i++) {
			TextField f = new TextField(String.valueOf(i));
			f.setWidth("30px");
			map.put(i, f);
		}
		
		
	}
	
	public TextField getF(int day) {
		if(map.containsKey(day)) {
			return map.get(day);
		}
		return null;
	}
	
	public void addT() {
		
		for(int i = 1; i <= LocalDate.now().lengthOfMonth(); i++) {
			TextField f = new TextField(String.valueOf(i));
			f.setWidth("30px");
			map.put(i, f);
		}
		
	}
	
	
//	public BigDecimal getSale(int day) {
//		if(map.containsKey(day)) {
//			return map.get(day);
//		}
//		
//		return null;
//	}
	
	
	
	

}
