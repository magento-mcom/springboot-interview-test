package com.adobe.bookstore.adapter.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.adobe.bookstore.adapter.web.exception.NotEnoughStockException;


public class BookstoreExceptionInterceptorTests {

	private BookstoreExceptionInterceptor tested;
	
	@BeforeEach
	void initAll() {
		this.tested = new BookstoreExceptionInterceptor();
	}

	@Test
	@DisplayName("Format exception")
	void formatExceptionTest() {
		// Given: A constraint violation exception
		ConstraintViolationException exception = new ConstraintViolationException("test-message", null);
		// Then: We call the function
		assertEquals(this.tested.formatException(exception), 
				new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST));
	}
	
	@Test
	@DisplayName("Not enough stock exception")
	void notEnoughStockExceptionTest() {
		// Given: A not enough stock exception
		NotEnoughStockException exception = new NotEnoughStockException("test-message");
		// Then: We call the function
		assertEquals(this.tested.notEnoughStockException(exception), 
				new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST));
	}
	
}
