package com.mango.customer.infrastructure.repository;

import com.mango.customer.domain.model.User;
import com.mango.customer.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JdbcUserRepositoryTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setup() {
		jdbcTemplate.execute("DELETE FROM users");
	}

	@Test
	void shouldInsertUserAndReturnWithId() {
		User user = new User(null, "Pedro", "Martinez", "Main St", "Tarragona", "pedro@example.com", true);

		User saved = userRepository.save(user);

		assertThat(saved.id()).isNotNull();
		assertThat(saved.email()).isEqualTo("pedro@example.com");
	}

	@Test
	void shouldReturnUserWhenExists() {
		jdbcTemplate.update("INSERT INTO users (id, name, last_name, address, city, email, agree_terms) VALUES (?, ?, ?, ?, ?, ?, ?)",
			1L, "Pedro", "Martinez", "Main St", "Tarragona", "pedro@example.com", true);

		Optional<User> user = userRepository.findById(1L);

		assertThat(user).isPresent();
		assertThat(user.get().name()).isEqualTo("Pedro");
	}

	@Test
	void shouldModifyExistingUser() {
		jdbcTemplate.update("INSERT INTO users (id, name, last_name, address, city, email, agree_terms) VALUES (?, ?, ?, ?, ?, ?, ?)",
			2L, "Pedro", "Martinez", "Main St", "Tarragona", "pedro@example.com", true);

		User updated = new User(2L, "Pedro", "Changed", "New St", "Barcelona", "pedro@example.com", false);

		userRepository.update(updated);

		User user = userRepository.findById(2L).orElseThrow();
		assertThat(user.lastName()).isEqualTo("Changed");
		assertThat(user.city()).isEqualTo("Barcelona");
		assertThat(user.agreeTerms()).isFalse();
	}


}
