package com.mango.customer.infrastructure.repository;

import com.mango.customer.domain.model.User;
import com.mango.customer.domain.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {
	private final JdbcTemplate jdbcTemplate;

	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User save(User user) {
		var keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO users (name, last_name, address, city, email, agree_terms) VALUES (?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
			);
			ps.setString(1, user.name());
			ps.setString(2, user.lastName());
			ps.setString(3, user.address());
			ps.setString(4, user.city());
			ps.setString(5, user.email());
			ps.setBoolean(6, user.agreeTerms());
			return ps;
		}, keyHolder);

		Long generatedId = keyHolder.getKey().longValue();
		return new User(generatedId, user.name(), user.lastName(), user.address(), user.city(), user.email(), user.agreeTerms());
	}

	@Override
	public Optional<User> findById(Long id) {
		return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new Object[]{id}, userRowMapper())
			.stream().findFirst();
	}

	@Override
	public User update(User user) {
		jdbcTemplate.update(
			"UPDATE users SET name = ?, last_name = ?, address = ?, city = ?, email = ?, agree_terms = ? WHERE id = ?",
			user.name(), user.lastName(), user.address(), user.city(), user.email(), user.agreeTerms(), user.id()
		);
		return user;
	}

	private RowMapper<User> userRowMapper() {
		return (ResultSet rs, int rowNum) -> new User(
			rs.getLong("id"),
			rs.getString("name"),
			rs.getString("last_name"),
			rs.getString("address"),
			rs.getString("city"),
			rs.getString("email"),
			rs.getBoolean("agree_terms")
		);
	}
}
