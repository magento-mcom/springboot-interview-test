package com.adobe.bookstore.adapter.web.exception;

public class NotEnoughStockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotEnoughStockException(final String message) {
		super(message);
	}

}
