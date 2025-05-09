package com.mango.customer.infrastructure.repository;

import com.mango.customer.domain.model.Slogan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JdbcSloganRepositoryTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private JdbcSloganRepository sloganRepository;

	private final Long userId = 1L;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute("DELETE FROM slogans");
		jdbcTemplate.execute("DELETE FROM users");
		jdbcTemplate.update("INSERT INTO users (id, name, last_name, address, city, email, agree_terms) VALUES (?, ?, ?, ?, ?, ?, ?)",
			userId, "name", "last_name", "address", "city", "test@example.com", true);
	}

	@Test
	void shouldInsertSloganAndReturnWithId() {
		Slogan slogan = new Slogan(null, userId, "Test slogan");

		Slogan saved = sloganRepository.save(slogan);

		assertThat(saved.id()).isNotNull();
		assertThat(saved.userId()).isEqualTo(userId);
		assertThat(saved.content()).isEqualTo("Test slogan");
	}

	@Test
	void shouldReturnAllSlogansForUser() {
		sloganRepository.save(new Slogan(null, userId, "First slogan"));
		sloganRepository.save(new Slogan(null, userId, "Second slogan"));

		List<Slogan> slogans = sloganRepository.findByUserId(userId);

		assertThat(slogans).hasSize(2);
	}

	@Test
	void shouldReturnSlogansNumber() {
		sloganRepository.save(new Slogan(null, userId, "First slogan"));
		sloganRepository.save(new Slogan(null, userId, "Second slogan"));

		long count = sloganRepository.countByUserId(userId);

		assertThat(count).isEqualTo(2);
	}

	@Test
	void shouldReturnEmptyList() {
		List<Slogan> slogans = sloganRepository.findByUserId(999L);
		assertThat(slogans).isEmpty();
	}

	@Test
	void shouldReturnZero() {
		long count = sloganRepository.countByUserId(999L);
		assertThat(count).isZero();
	}
}
