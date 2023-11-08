package com.example.appservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appservice.service.WordDict;

@ExtendWith(value = { MockitoExtension.class })
public class WordDictTets {

	@Mock
	private Map<String, String> map;

	@InjectMocks
	private WordDict wordDict;
//	if injecmock is interface should be initilized e.g 
//	@InjectMocks
//	private Dict dict = new WordDic(); 

	// this method is passed
	@Test
	public void testMeaning() {
		when(map.get("a")).thenReturn("sdf");
		assertEquals("sdf", wordDict.getMeaning("a"));
	}

}
