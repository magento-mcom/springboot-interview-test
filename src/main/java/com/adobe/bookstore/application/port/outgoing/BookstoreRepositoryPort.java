package com.adobe.bookstore.application.port.outgoing;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.adobe.bookstore.domain.Book;

public interface BookstoreRepositoryPort {

	/**
	 * Initial books stock to save
	 * 
	 * @param books books to save
	 */
	void save(List<Book> books);
	
	/**
	 * Find by book ID
	 * 
	 * @param bookId book id
	 * @return optional of book
	 */
	Optional<Book> findById(UUID bookId);
}
