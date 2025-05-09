package com.mango.customer.domain.model;

public record User(
	Long id,
	String name,
	String lastName,
	String address,
	String city,
	String email,
	boolean agreeTerms
) {}
