package com.mango.customer.infrastructure.rest;

import com.mango.customer.application.SloganService;
import com.mango.customer.domain.model.Slogan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaigns")
public class SloganController {

	private final SloganService sloganService;

	public SloganController(SloganService sloganService) {
		this.sloganService = sloganService;
	}

	@PostMapping("/slogan")
	public ResponseEntity<?> addSlogan(@RequestBody Slogan slogan) {
		return new ResponseEntity<>(sloganService.addSlogan(slogan), HttpStatus.CREATED);
	}
}
