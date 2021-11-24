package com.adobe.bookstore.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.adobe.bookstore.application.port.outgoing.BookstoreRepositoryPort;
import com.adobe.bookstore.application.port.outgoing.OrderRepositoryPort;
import com.adobe.bookstore.domain.Book;
import com.adobe.bookstore.domain.Order;

class BookstoreServiceTests {
	
	private BookstoreService tested;
	
	private BookstoreRepositoryPort bookstoreRepositoryPort;
	
	private OrderRepositoryPort orderRepositoryPort;

	@BeforeEach
	void initAll() {
		this.bookstoreRepositoryPort = mock(BookstoreRepositoryPort.class);
		this.orderRepositoryPort = mock(OrderRepositoryPort.class);
		this.tested = new BookstoreService(this.bookstoreRepositoryPort, this.orderRepositoryPort);
	}
	
	@Test
	@DisplayName("Create -> OK")
	void createTest() {
		// Given: A book ID
		UUID bookId = UUID.randomUUID();
		// And: A book name
		String bookName = "book-name";
		// And: A books order
		List<Book> booksOrder = List.of(new Book(bookId, bookName, 1));
		// And: Same book in the stock
		Book book = new Book(bookId, bookName, 2);
		// And: An order
		Order order = new Order(UUID.randomUUID(), booksOrder);
		// When: We find the book by id
		when(this.bookstoreRepositoryPort.findById(bookId)).thenReturn(Optional.of(book));
		// And: We create an order
		this.orderRepositoryPort.create(order);
		// And: We update the current stock
		this.bookstoreRepositoryPort.save(List.of(book));
		// Then: We check if order is returned
		assertThat(this.tested.create(booksOrder).isPresent());
	}
	
	@Test
	@DisplayName("Create -> KO")
	void notCreateTest() {
		// Given: A book ID
		UUID bookId = UUID.randomUUID();
		// And: A book name
		String bookName = "book-name";
		// And: A books order
		List<Book> booksOrder = List.of(new Book(bookId, bookName, 1));
		// And: Same book in the stock
		Book book = new Book(bookId, bookName, 0);
		// When: We find the book by id
		when(this.bookstoreRepositoryPort.findById(bookId)).thenReturn(Optional.of(book));
		// Then: We check if order is returned
		assertThat(this.tested.create(booksOrder).isEmpty());
	}
	
	@Test
	@DisplayName("Retrieve")
	void retrieveTest() {
		// Given: A list of orders
		List<Order> orders = List.of(new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "test-name", 1))));
		// When: We retrieve the orders
		when(this.orderRepositoryPort.retrieve()).thenReturn(orders);
		// Then: Check if the list is correct
		assertEquals(this.tested.retrieve(), orders);
	}
	
}
