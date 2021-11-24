package com.adobe.bookstore.adapter.persistence.h2;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adobe.bookstore.application.port.outgoing.BookstoreRepositoryPort;
import com.adobe.bookstore.domain.Book;

@Component
public class BookstorePersistenceAdapter implements BookstoreRepositoryPort {
	
	private final BookstoreRepository bookstoreRepository;
	
	@Autowired
	public BookstorePersistenceAdapter(final BookstoreRepository bookstoreRepository) {
		this.bookstoreRepository = bookstoreRepository;
	}

	@Override
	public void save(final List<Book> books) {
		this.bookstoreRepository.saveAll(books);
	}
	
	public Optional<Book> findById(final UUID bookId) {
		return this.bookstoreRepository.findById(bookId);
	}
	
	// http://localhost:8080/h2-console/login.jsp?jsessionid=ac1870fc2ab2502c0d98c5b4242ce4fd
}
