package com.mango.customer.infrastructure.rest;

import com.mango.customer.application.UserService;
import com.mango.customer.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Test
	void shouldReturnCreatedUser() {
		User user = new User(1L, "Pedro", "Changed", "Av de la paz", "Barcelona", "pedro@example.com", false);

		when(userService.register(any(User.class))).thenReturn(user);

		ResponseEntity<User> response = userController.createUser(user);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(user, response.getBody());
		verify(userService).register(user);
	}

	@Test
	void shouldReturnUpdatedUser() {
		User userToUpdate = new User(1L, "Pedro", "Changed", "New address", "Barcelona", "pedro@example.com", false);

		when(userService.update(any(User.class))).thenReturn(userToUpdate);

		ResponseEntity<User> response = userController.update(userToUpdate);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userToUpdate, response.getBody());
		verify(userService).update(userToUpdate);
	}

}
