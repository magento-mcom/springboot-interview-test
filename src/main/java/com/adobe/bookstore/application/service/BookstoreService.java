package com.adobe.bookstore.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.adobe.bookstore.application.port.ingoing.BookstoreServicePort;
import com.adobe.bookstore.application.port.outgoing.BookstoreRepositoryPort;
import com.adobe.bookstore.application.port.outgoing.OrderRepositoryPort;
import com.adobe.bookstore.domain.Book;
import com.adobe.bookstore.domain.Order;

public class BookstoreService implements BookstoreServicePort {
	
	private final BookstoreRepositoryPort bookstoreRepositoryPort;
	
	private final OrderRepositoryPort orderRepositoryPort;

	public BookstoreService(final BookstoreRepositoryPort bookstoreRepositoryPort, 
			final OrderRepositoryPort orderRepositoryPort) {
		this.bookstoreRepositoryPort = bookstoreRepositoryPort;
		this.orderRepositoryPort = orderRepositoryPort;
	}
	
	@Override
	public Optional<Order> create(List<Book> booksOrder) {
		List<Book> booksToUpdate = new ArrayList<Book>();
		List<Book> validOrder = booksOrder.stream().filter(book -> isValid(book, booksToUpdate)).collect(Collectors.toList());
		if (validOrder.size() == booksOrder.size()) {
			// Generate a new order
			Order newOrder = new Order(UUID.randomUUID(), booksOrder);
			this.orderRepositoryPort.create(newOrder);
			// Update the stock
			this.bookstoreRepositoryPort.save(booksToUpdate);
			return Optional.of(newOrder);
		}
		return Optional.empty();
	}

	private boolean isValid(Book book, List<Book> booksToUpdate) {
		Optional<Book> stockBook = this.bookstoreRepositoryPort.findById(book.getId());
		if (stockBook.isPresent()) {
			int stockQuantity = stockBook.get().getQuantity();
			if (stockQuantity >= book.getQuantity()) {
				// We take the opportunity to introduce the possible books to update
				Book updatedBook = new Book(book.getId(), book.getName(), book.getQuantity());
				int remainingQuantity = stockQuantity - book.getQuantity();
				if (remainingQuantity < 0) updatedBook.setQuantity(0);
				else updatedBook.setQuantity(remainingQuantity);
				booksToUpdate.add(updatedBook);
				return true;
			}
			else return false;
		}
		else return false;
	}

	@Override
	public List<Order> retrieve() {
		return this.orderRepositoryPort.retrieve();
	}

}
