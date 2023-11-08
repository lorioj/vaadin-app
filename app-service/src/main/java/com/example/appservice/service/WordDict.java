package com.example.appservice.service;

import java.util.Map;
//this class is sample for spring test
public class WordDict {
	private Map<String, String> map;

	public String getMeaning(String k) {
		return map.get(k);
	}

}
