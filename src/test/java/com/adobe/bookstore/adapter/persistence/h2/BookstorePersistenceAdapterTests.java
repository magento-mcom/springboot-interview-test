package com.adobe.bookstore.adapter.persistence.h2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.adobe.bookstore.domain.Book;

class BookstorePersistenceAdapterTests {

	private BookstorePersistenceAdapter tested;
	
	private BookstoreRepository bookstoreRepository;

	@BeforeEach
	void initAll() {
		this.bookstoreRepository = mock(BookstoreRepository.class);
		this.tested = new BookstorePersistenceAdapter(this.bookstoreRepository);
	}

	@Test
	@DisplayName("Save")
	void saveTest() {
		// Given: A list of books
		List<Book> books = List.of(new Book(UUID.randomUUID(), "test-name", 1), new Book(UUID.randomUUID(), "name-test", 3));
		// When: Call the repository
		when(this.bookstoreRepository.saveAll(books)).thenReturn(books);
		// Then: Save the books
		this.tested.save(books);
	}
	
	@Test
	@DisplayName("Find by ID")
	void findByIdTest() {
		// Given: A book ID
		UUID bookId = UUID.randomUUID();
		// When: Call the repository
		when(this.bookstoreRepository.findById(bookId)).thenReturn(Optional.of(new Book(bookId)));
		// Then: Save the books
		this.tested.findById(bookId);
	}

}
