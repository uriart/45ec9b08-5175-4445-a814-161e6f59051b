package com.mango.customer.domain.repository;

import com.mango.customer.domain.model.Slogan;

import java.util.List;

public interface SloganRepository {
	Slogan save(Slogan slogan);
	List<Slogan> findByUserId(Long userId);
	long countByUserId(Long userId);
}
