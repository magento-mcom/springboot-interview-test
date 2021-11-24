package com.adobe.bookstore.domain;

import java.util.List;
import java.util.UUID;

public class Order {

	private UUID id;
	
	private List<Book> books;
	
	public Order(final UUID id, final List<Book> books) {
		this.id = id;
		this.books = books;
	}

	public UUID getId() {
		return id;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
}
