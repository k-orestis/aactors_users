package com.agileactors.usersproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application_test.properties")
class UsersProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
