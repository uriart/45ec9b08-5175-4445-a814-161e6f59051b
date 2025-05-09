package com.mango.customer.infrastructure.rest;

import com.mango.customer.application.SloganService;
import com.mango.customer.domain.model.Slogan;
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
class SloganControllerTest {

	@Mock
	private SloganService sloganService;

	@InjectMocks
	private SloganController sloganController;

	@Test
	void shouldReturnCreatedSlogan() {
		Slogan inputSlogan = new Slogan(1L, 1L, "My slogan");

		when(sloganService.addSlogan(any(Slogan.class))).thenReturn(inputSlogan);

		ResponseEntity<?> response = sloganController.addSlogan(inputSlogan);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(inputSlogan, response.getBody());
		verify(sloganService).addSlogan(inputSlogan);
	}
}
