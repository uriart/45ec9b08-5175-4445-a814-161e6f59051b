package com.mango.customer.infrastructure.repository;

import com.mango.customer.domain.model.Slogan;
import com.mango.customer.domain.repository.SloganRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcSloganRepository implements SloganRepository {
	private final JdbcTemplate jdbcTemplate;

	public JdbcSloganRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Slogan save(Slogan slogan) {
		var keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			var ps = connection.prepareStatement(
				"INSERT INTO slogans (user_id, content) VALUES (?, ?)",
				Statement.RETURN_GENERATED_KEYS
			);
			ps.setLong(1, slogan.userId());
			ps.setString(2, slogan.content());
			return ps;
		}, keyHolder);
		Long generatedId = keyHolder.getKey().longValue();
		return new Slogan(generatedId, slogan.userId(), slogan.content());
	}

	@Override
	public List<Slogan> findByUserId(Long userId) {
		return jdbcTemplate.query(
			"SELECT * FROM slogans WHERE user_id = ?",
			new Object[]{userId},
			sloganRowMapper()
		);
	}

	@Override
	public long countByUserId(Long userId) {
		Long count =  jdbcTemplate.queryForObject(
			"SELECT COUNT(*) FROM slogans WHERE user_id = ?",
			new Object[]{userId},
			Long.class
		);
		return count != null ? count : 0L;
	}

	private RowMapper<Slogan> sloganRowMapper() {
		return (ResultSet rs, int rowNum) -> new Slogan(
			rs.getLong("id"),
			rs.getLong("user_id"),
			rs.getString("content")
		);
	}
}
