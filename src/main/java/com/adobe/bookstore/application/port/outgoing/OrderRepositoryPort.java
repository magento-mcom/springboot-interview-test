package com.adobe.bookstore.application.port.outgoing;

import java.util.List;

import com.adobe.bookstore.domain.Order;

public interface OrderRepositoryPort {

	/**
	 * Create an order
	 * 
	 * @param order order to create
	 */
	void create(Order order);
	
	/**
	 * Retrieve all orders
	 * 
	 * @return order list
	 */
	List<Order> retrieve();
}
