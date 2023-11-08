package com.example.appservice.model;

//this class is sample for spring test
public class Student {

	private Pen pen;

	public String write() {
		return "The color of pen: " + pen.getPenColor();
	}

}
