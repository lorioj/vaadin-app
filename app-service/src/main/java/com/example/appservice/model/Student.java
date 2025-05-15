package com.example.appservice.model;

//this class is sample for spring test
public class Student {

	private Pen pen;

	public String write(String s) {
		if(s == null) {
			return null;
		}

		return "The color of pen: " + pen.getPenColor();
	}

}
