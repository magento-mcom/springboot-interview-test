package com.adobe.bookstore.adapter.web;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adobe.bookstore.adapter.web.validator.FormatConstraint;
import com.adobe.bookstore.domain.Book;
import com.adobe.bookstore.domain.Order;

@Validated
@RequestMapping
public interface BookstoreApi {
	
	/**
	 * 
	 * Create a new Order:
	 *
	 * The application receives orders in a JSON format through an HTTP API endpoint (POST).
	 * Orders contain a list of books and the quantity.
	 * Before registering the order, the system should check if there's enough stock to fulfill the order.
	 * This JSON file contains stock availability (stock.json)
	 * If one of the books in the order does not have enough stock we will reject the entire order.
	 * After stock validation, the order is marked as a success, and it would return a Unique Order Identifier to the caller of the HTTP API endpoint.
	 * If the order was processed we need to update available stock, taking into consideration:
	 * Updating stock should not be a blocker for replying to the customer.
	 * If the process of updating stock fails, should not cause an error in order processing.
	 * 
	 * @param order requested order
	 * @return order ID
	 */
	@PostMapping(path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
	default ResponseEntity<UUID> create(@RequestBody List<Book> order) {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}
	
	/**
	 * 
	 * Retrieve Orders:
	 * 
	 * The application has an endpoint to extract a list of existing orders.
	 * The endpoint has a request parameter to indicate the format of requested data. It can be JSON or CSV.
	 * 
	 * @param response HTTP servlet response
	 * @param format format of requested data. It can be JSON or CSV.
	 * @return list of existing orders (JSON or CSV)
	 * @throws IOException if CSV cannot be generated
	 */
	@GetMapping(path = "/orders", produces = { MediaType.APPLICATION_JSON_VALUE, "text/csv" })
	default ResponseEntity<List<Order>> retrieve(HttpServletResponse response, @FormatConstraint @RequestParam(name = "format") String format) throws IOException {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

}
