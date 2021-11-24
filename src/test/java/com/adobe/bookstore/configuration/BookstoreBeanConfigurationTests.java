package com.adobe.bookstore.configuration;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.adobe.bookstore.application.port.outgoing.BookstoreRepositoryPort;
import com.adobe.bookstore.application.port.outgoing.OrderRepositoryPort;
import com.adobe.bookstore.application.service.BookstoreService;

class BookstoreBeanConfigurationTests {

	private BookstoreBeanConfiguration tested;
	
	@BeforeEach
	void initAll() {
		this.tested = new BookstoreBeanConfiguration();
	}
	
	@Test
	@DisplayName("Bean creation")
	void bookstoreServiceTest() {
		// Given: A book repository port mock
		BookstoreRepositoryPort bookstoreRepositoryPort = mock(BookstoreRepositoryPort.class);
		// And: A Order repository port mock
		OrderRepositoryPort orderRepositoryPort = mock(OrderRepositoryPort.class);
		// Then: Check that bean has the correct type
		Assert.isInstanceOf(BookstoreService.class, this.tested.bookstoreService(bookstoreRepositoryPort, orderRepositoryPort));
	}
	
}
