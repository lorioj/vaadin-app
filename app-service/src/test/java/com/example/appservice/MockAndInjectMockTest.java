package com.example.appservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.appservice.model.Pen;
import com.example.appservice.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//this class is sample for spring test
@ExtendWith(value = { MockitoExtension.class })
public class MockAndInjectMockTest {

	@Mock
	private Pen pen;

	@InjectMocks
	private Student student;

	@Test
	public void testWrite() {
		when(pen.getPenColor()).thenReturn("red");
		assertEquals("The color of pen: red", student.write("d"));
	}

}
