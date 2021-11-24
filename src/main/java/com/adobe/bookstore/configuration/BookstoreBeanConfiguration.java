package com.adobe.bookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adobe.bookstore.application.port.outgoing.BookstoreRepositoryPort;
import com.adobe.bookstore.application.port.outgoing.OrderRepositoryPort;
import com.adobe.bookstore.application.service.BookstoreService;

@Configuration
public class BookstoreBeanConfiguration {

	@Bean
	public BookstoreService bookstoreService(final BookstoreRepositoryPort bookstoreRepositoryPort, 
			final OrderRepositoryPort orderRepositoryPort) {
		return new BookstoreService(bookstoreRepositoryPort, orderRepositoryPort);
	}
	
}
