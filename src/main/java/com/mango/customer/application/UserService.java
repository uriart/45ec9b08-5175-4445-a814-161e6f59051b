package com.mango.customer.application;

import com.mango.customer.application.exceptions.UserNotFoundException;
import com.mango.customer.domain.model.User;
import com.mango.customer.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User register(User user) {
		return userRepository.save(user);
	}

	public User update(User user) {
		if (!userExists(user.id())) {
			throw new UserNotFoundException(user.id());
		}
		return userRepository.update(user);
	}

	public boolean userExists(Long userId) {
		return userRepository.findById(userId).isPresent();
	}
}
