package com.example.application.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeEntryHelper {

	private String name;
	
	private Map<Integer, BigDecimal> timeEntries;
	
	public TimeEntryHelper() {
		timeEntries = new HashMap<>();
	}
	
	public void setTimeEntries(int day, String newEntry) {
		if(newEntry.equals("new")) {
			for(int i = 1; i <= day; i++) {
				timeEntries.put(i, null);
			}
		}else {
			for(int i = 1; i <= day; i++) {
				timeEntries.put(i, new BigDecimal(Math.random() * 9).setScale(2, RoundingMode.HALF_UP));
			}
		}
		
	}

	
//	db
//	public void setTimeEntry(int k, BigDecimal v) {
//		if(timeEntries.containsKey(k)) {
//			timeEntries.put(k, v);
//		}else {
//			//not found
//		}
//	}
}
