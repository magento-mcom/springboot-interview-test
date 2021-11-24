package com.adobe.bookstore.adapter.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;


import com.adobe.bookstore.adapter.web.csv.CsvGenerator;
import com.adobe.bookstore.adapter.web.exception.NotEnoughStockException;
import com.adobe.bookstore.application.port.ingoing.BookstoreServicePort;
import com.adobe.bookstore.domain.Book;
import com.adobe.bookstore.domain.Order;

class BookstoreControllerTests {

	private BookstoreController tested;
	
	private BookstoreServicePort bookstoreServicePort;
	
	private CsvGenerator csvGenerator;
	
	@BeforeEach
	void initAll() {
		this.bookstoreServicePort = mock(BookstoreServicePort.class);
		this.csvGenerator = mock(CsvGenerator.class);
		this.tested = new BookstoreController(this.bookstoreServicePort, this.csvGenerator);
	}

	@Test
	@DisplayName("Create -> OK")
	void createTest() {
		// Given: A list of books order
		List<Book> booksOrder = List.of(new Book(UUID.randomUUID(), "test-name", 1));
		// And: An order
		Order order = new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "test-name", 1)));
		// When: We create the order
		when(this.bookstoreServicePort.create(booksOrder)).thenReturn(Optional.of(order));
		// Then: We check if order has been created correctly
		assertEquals(this.tested.create(booksOrder), ResponseEntity.ok(order.getId()));
	}
	
	@Test
	@DisplayName("Create -> KO")
	void notEnoughStockTest() {
		// Given: A list of books order
		List<Book> booksOrder = List.of(new Book(UUID.randomUUID(), "test-name", 1));
		// When: We create the order
		when(this.bookstoreServicePort.create(booksOrder)).thenReturn(Optional.empty());
		// Then: We check that the exception is thrown
		assertThrows(NotEnoughStockException.class, () -> this.tested.create(booksOrder));
	}
	
	@Test
	@DisplayName("Retrieve JSON")
	void retrieveJsonTest() throws IOException {
		// Given: A response
		HttpServletResponse response = mock(HttpServletResponse.class);
		// And: A format
		String format = "JSON";
		// And: an order list
		List<Order> orderList = List.of(new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "test-name", 1))));
		// When: We retrieve the orders
		when(this.bookstoreServicePort.retrieve()).thenReturn(orderList);
		// Then: Check if the response is correct
		assertEquals(this.tested.retrieve(response, format), ResponseEntity.ok(orderList));
	}
	
	@Test
	@DisplayName("Retrieve CSV")
	void retrieveCsvTest() throws IOException {
		// Given: A response
		HttpServletResponse response = mock(HttpServletResponse.class);
		// And: A format
		String format = "CSV";
		// And: an order list
		List<Order> orderList = List.of(new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "test-name", 1))));
		// When: We retrieve the orders
		when(this.bookstoreServicePort.retrieve()).thenReturn(orderList);
		// And: We generate the csv
		this.csvGenerator.generateCsv(response, orderList);
		// Then: Check if the response is correct
		assertEquals(this.tested.retrieve(response, format), ResponseEntity.ok().build());
	}
	
}
