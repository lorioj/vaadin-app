package com.example.appservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import com.example.appservice.model.User;

@JsonTest
public class UserControllerTest {

//	@Value("classpath:single.json")
//	private Resource singleJsonContent;

	@Autowired
	private JacksonTester<User> json;

	@Autowired
	private JacksonTester<User[]> jsonList;

	private User[] users;

	@BeforeEach
	void setUp() {
		users = Arrays.array(User.builder().id(1L).name("admin").build(), User.builder().id(2L).name("test").build(),
				User.builder().id(3L).name("john").build());

	}

	@Test
	public void userSerializationTest() throws IOException {
		User user = users[0];
		assertThat(json.write(user)).isStrictlyEqualToJson(new ClassPathResource("single.json"));
		assertThat(json.write(user)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(user)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
		assertThat(json.write(user)).hasJsonPathStringValue("@.name");
		assertThat(json.write(user)).extractingJsonPathStringValue("@.name").isEqualTo("admin");
//	
	}

	@Test
	public void userDeserializationTest() throws IOException {
		String expected = "{ \"id\" : \"1\", \"name\" : \"admin\" }";
		assertThat(json.parseObject(expected).getId()).isEqualTo(1L);
		assertThat(json.parseObject(expected).getName()).isEqualTo("admin");
		
	
		var single = json.read(new ClassPathResource("single.json")).getObject();
		assertThat(single.getName()).isEqualTo("admin");
	}

	@Test
	void userListSerializationTest() throws IOException {
		assertThat(jsonList.write(users)).isStrictlyEqualToJson(new ClassPathResource("list.json"));
	}
	
	
	

}
