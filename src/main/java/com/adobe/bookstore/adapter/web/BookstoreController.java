package com.adobe.bookstore.adapter.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.bookstore.adapter.web.csv.CsvGenerator;
import com.adobe.bookstore.adapter.web.exception.NotEnoughStockException;
import com.adobe.bookstore.application.port.ingoing.BookstoreServicePort;
import com.adobe.bookstore.domain.Book;
import com.adobe.bookstore.domain.Order;

@RestController
public class BookstoreController implements BookstoreApi {
	
	private static final String CSV = "CSV";
	
	private final BookstoreServicePort bookstoreServicePort;
	
	private final CsvGenerator csvGenerator;

	@Autowired
	public BookstoreController(final BookstoreServicePort bookstoreServicePort, final CsvGenerator csvGenerator) {
		this.bookstoreServicePort = bookstoreServicePort;
		this.csvGenerator = csvGenerator;
	}
	
	@Override
	public ResponseEntity<UUID> create(List<Book> booksOrder) {
		Optional<Order> newOrder = this.bookstoreServicePort.create(booksOrder);
		if (newOrder.isPresent()) return ResponseEntity.ok(newOrder.get().getId());
		else throw new NotEnoughStockException("Not enough stock to process the order.");
	}
	
	@Override
	public ResponseEntity<List<Order>> retrieve(HttpServletResponse response, String format) throws IOException {
		List<Order> orders = this.bookstoreServicePort.retrieve();
		if (format.equals(CSV)) {
			this.csvGenerator.generateCsv(response, orders);
			return ResponseEntity.ok().build();
		}
		else return ResponseEntity.ok(orders); 
	}
}
