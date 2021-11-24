package com.adobe.bookstore.adapter.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adobe.bookstore.adapter.web.BookstoreController;
import com.adobe.bookstore.adapter.web.exception.NotEnoughStockException;

@ControllerAdvice(assignableTypes = BookstoreController.class)
public class BookstoreExceptionInterceptor {

	@ExceptionHandler
	public ResponseEntity<String> formatException(final ConstraintViolationException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> notEnoughStockException(final NotEnoughStockException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
