package com.mango.customer.application;

import com.mango.customer.application.exceptions.MaxSlogansReachedException;
import com.mango.customer.application.exceptions.UserNotFoundException;
import com.mango.customer.domain.model.Slogan;
import com.mango.customer.domain.repository.SloganRepository;
import org.springframework.stereotype.Service;

@Service
public class SloganService {
	private final SloganRepository sloganRepository;
	private final UserService userService;

	public SloganService(SloganRepository sloganRepository, UserService userService) {
		this.sloganRepository = sloganRepository;
		this.userService = userService;
	}

	public boolean canAddSlogan(Long userId) {
		return sloganRepository.countByUserId(userId) < 3;
	}

	public Slogan addSlogan(Slogan slogan) {
		if (!userService.userExists(slogan.userId())) {
			throw new UserNotFoundException(slogan.userId());
		}

		if (!canAddSlogan(slogan.userId())) {
			throw new MaxSlogansReachedException("Maximum number of slogans reached for user " + slogan.userId());
		}

		return sloganRepository.save(slogan);
	}

}
