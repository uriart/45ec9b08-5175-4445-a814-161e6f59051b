package com.mango.customer.domain.repository;

import com.mango.customer.domain.model.User;

import java.util.Optional;

public interface UserRepository {
	User save(User user);
	Optional<User> findById(Long id);
	User update(User user);
}
