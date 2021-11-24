package com.adobe.bookstore.application.port.ingoing;

import java.util.List;
import java.util.Optional;

import com.adobe.bookstore.domain.Book;
import com.adobe.bookstore.domain.Order;

public interface BookstoreServicePort {

	/**
	 * Create an order
	 * 
	 * @param booksOrder books to generate a new order
	 * @return optional of order (if enough stock)
	 */
	Optional<Order> create(List<Book> booksOrder);
	
	/**
	 * Retrieve all orders
	 * 
	 * @return order list
	 */
	List<Order> retrieve();
}
