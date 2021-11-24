package com.adobe.bookstore.adapter.web.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FormatValidatorTests {

	private FormatValidator tested;
	
	@BeforeEach
	void initAll() {
		this.tested = new FormatValidator();
	}
	
	@Test
	@DisplayName("Valid -> OK")
	void validTest() {
		// Given: A value
		String value = "JSON";
		// And: A mocked context
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		// When: We validate the value
		boolean isValid = this.tested.isValid(value, context);
		// Then: Check if the result is correct
		assertTrue(isValid);
	}
	
	@Test
	@DisplayName("Not valid")
	void notValidTest() {
		// Given: A value
		String value = "INVALID_FORMAT";
		// And: A mocked context
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		// When: We validate the value
		boolean isValid = this.tested.isValid(value, context);
		// Then: Check if the result is correct
		assertFalse(isValid);
	}
	
}
