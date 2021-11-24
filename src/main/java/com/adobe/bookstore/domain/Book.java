package com.adobe.bookstore.domain;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	public Book() {
		
	}
	
	public Book(UUID id) {
		this.id = id;
	}
	
	public Book(UUID id, String name, int quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", quantity=" + quantity + "]\n";
	}
	
}
