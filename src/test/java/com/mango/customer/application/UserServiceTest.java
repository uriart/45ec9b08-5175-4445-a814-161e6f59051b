package com.mango.customer.application;

import com.mango.customer.application.exceptions.UserNotFoundException;
import com.mango.customer.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserService userService;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute("DELETE FROM slogans");
		jdbcTemplate.execute("DELETE FROM users");
	}

	@Test
	void shouldFailUpdateUserWhithUserNotFoundException() {
		User user = new User(null, "Miguel", "Garcia", "Address", "City", "miguel@mango.com", true);

		assertThrows(UserNotFoundException.class, () -> {
			userService.update(user);
		});
	}

}
