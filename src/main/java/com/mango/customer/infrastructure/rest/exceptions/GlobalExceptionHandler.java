package com.mango.customer.infrastructure.rest.exceptions;

import com.mango.customer.application.exceptions.MaxSlogansReachedException;
import com.mango.customer.application.exceptions.UserNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String MAX_SLOGANS_REACHED = "MAX_SLOGANS_REACHED";
	public static final String EMAIL_ALREADY_EXISTS = "EMAIL_ALREADY_EXISTS";
	public static final String USER_NOT_FOUND = "USER_NOT_FOUND";

	@ExceptionHandler(MaxSlogansReachedException.class)
	public ResponseEntity<ErrorResponse> maxSlogansReachedException(MaxSlogansReachedException ex) {
		ErrorResponse errorResponse = new ErrorResponse(
			MAX_SLOGANS_REACHED,
			ex.getMessage()
		);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<ErrorResponse> existingEmail(DuplicateKeyException ex) {
		ErrorResponse errorResponse = new ErrorResponse(
			EMAIL_ALREADY_EXISTS,
			ex.getMessage()
		);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> existingEmail(UserNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(
			USER_NOT_FOUND,
			ex.getMessage()
		);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
