package com.mango.customer.application;

import com.mango.customer.application.exceptions.MaxSlogansReachedException;
import com.mango.customer.application.exceptions.UserNotFoundException;
import com.mango.customer.domain.model.Slogan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SloganServiceTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SloganService sloganService;

	private final Long userId = 1L;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute("DELETE FROM slogans");
		jdbcTemplate.execute("DELETE FROM users");
	}

	@Test
	void shouldFailAddSloganWhithUserNotFoundException() {
		Slogan slogan = new Slogan(userId, 1L, "My slogan");

		assertThrows(UserNotFoundException.class, () -> {
			sloganService.addSlogan(slogan);
		});
	}

	@Test
	void shouldFailAddSloganWhithMaxSlogansReachedException() {
		jdbcTemplate.update("INSERT INTO users (id, name, last_name, address, city, email, agree_terms) VALUES (?, ?, ?, ?, ?, ?, ?)",
			userId, "name", "last_name", "address", "city", "test@example.com", true);

		Slogan slogan = new Slogan(null, userId, "My slogan");
		sloganService.addSlogan(slogan);
		sloganService.addSlogan(slogan);
		sloganService.addSlogan(slogan);

		assertThrows(MaxSlogansReachedException.class, () -> {
			sloganService.addSlogan(slogan);
		});
	}
}
