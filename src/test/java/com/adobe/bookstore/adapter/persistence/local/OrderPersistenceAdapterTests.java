package com.adobe.bookstore.adapter.persistence.local;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.adobe.bookstore.domain.Book;
import com.adobe.bookstore.domain.Order;

class OrderPersistenceAdapterTests {

	private OrderPersistenceAdapter tested;
	
	@BeforeEach
	void initAll() {
		this.tested = new OrderPersistenceAdapter();
	}

	@Test
	@DisplayName("Create and retrieve")
	void createTest() {
		// Given: An order
		Order order = new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "test-name", 1)));
		// When: We create an order
		this.tested.create(order);
		// Then: We check if the order has been created correctly
		assertEquals(order, this.tested.retrieve().get(0));
	}

}
