package com.mango.customer.infrastructure.rest;

import com.mango.customer.application.UserService;
import com.mango.customer.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signin")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<User> update(@RequestBody User user) {
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}
}
