package com.adobe.bookstore.adapter.persistence.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adobe.bookstore.application.port.outgoing.OrderRepositoryPort;
import com.adobe.bookstore.domain.Order;

@Component
public class OrderPersistenceAdapter implements OrderRepositoryPort {
	
	private List<Order> orders;
	
	public OrderPersistenceAdapter() {
		this.orders = new ArrayList<>();
	}

	@Override
	public void create(Order order) {
		this.orders.add(order);
	}

	@Override
	public List<Order> retrieve() {
		return this.orders;
	}

}
