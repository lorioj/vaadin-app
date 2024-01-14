package com.example.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.helper.TimeEntryHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TimeEntryService {

	public List<TimeEntryHelper> getRows(int monthLength) {
		List<TimeEntryHelper> res = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			TimeEntryHelper h = new TimeEntryHelper();
			h.setName("john");
			h.setTimeEntries(monthLength, "");
			res.add(h);
		}

		return res;

	}

}
