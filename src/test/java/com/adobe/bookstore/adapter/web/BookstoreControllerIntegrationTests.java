package com.adobe.bookstore.adapter.web;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.adobe.bookstore.adapter.web.csv.CsvGenerator;
import com.adobe.bookstore.application.port.ingoing.BookstoreServicePort;
import com.adobe.bookstore.domain.Book;
import com.adobe.bookstore.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookstoreControllerIntegrationTests {
	
	private BookstoreServicePort bookstoreServicePort;
	
	private CsvGenerator csvGenerator;
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
	
	@BeforeEach
	void initAll() {
		this.bookstoreServicePort = mock(BookstoreServicePort.class);
		this.csvGenerator = mock(CsvGenerator.class);
	}
	
	@Test
	@DisplayName("Create -> OK")
	void createIntegrationTest() throws Exception {
		// Given: A list of books order
		List<Book> booksOrder = 
				List.of(new Book(UUID.fromString("22d580fc-d02e-4f70-9980-f9693c18f6e0"), "dolore aliqua sint ipsum laboris", 1));
		// And: An order
		Order order = new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "dolore aliqua sint ipsum laboris", 1)));
		// When: We create the order
		when(this.bookstoreServicePort.create(booksOrder)).thenReturn(Optional.of(order));
		// Then: We call the tested endpoint
	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/orders")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.objectMapper.writeValueAsString(booksOrder));
	    this.mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()));
	}
	
	@Test
	@DisplayName("Create -> KO")
	void notCreateIntegrationTest() throws Exception {
		// Given: A list of books order
		List<Book> booksOrder = 
				List.of(new Book(UUID.randomUUID(), "not-existing-book", 1));
		// And: An order
		Order order = new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "not-existing-book", 1)));
		// When: We create the order
		when(this.bookstoreServicePort.create(booksOrder)).thenReturn(Optional.of(order));
		// Then: We call the tested endpoint
	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/orders")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.objectMapper.writeValueAsString(booksOrder));
	    this.mockMvc.perform(mockRequest)
	            .andExpect(status().isBadRequest())
	            .andExpect(jsonPath("$", notNullValue()));
	}
	
	@Test
	@DisplayName("Retrieve -> JSON")
	void retrieveJsonIntegrationTest() throws Exception {
		// Given: An order list
		List<Order> orders = 
				List.of(new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "dolore aliqua sint ipsum laboris", 1))));
		// And: A format
		String format = "JSON";
		// When: We retrieve the orders
		when(this.bookstoreServicePort.retrieve()).thenReturn(orders);
		// Then: We call the tested endpoint
	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/orders")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .param("format", format);
	    this.mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()));
	}
	
	@Test
	@DisplayName("Retrieve -> CSV")
	void retrieveCsvIntegrationTest() throws Exception {
		// Given: An order list
		List<Order> orders = 
				List.of(new Order(UUID.randomUUID(), List.of(new Book(UUID.randomUUID(), "dolore aliqua sint ipsum laboris", 1))));
		// And: A response
		HttpServletResponse response = mock(HttpServletResponse.class);
		// And: A format
		String format = "CSV";
		// When: We retrieve the orders
		when(this.bookstoreServicePort.retrieve()).thenReturn(orders);
		// And: Call the CSV generation
		doNothing().when(this.csvGenerator).generateCsv(response, orders);
		// Then: We call the tested endpoint
	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/orders")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .param("format", format);
	    this.mockMvc.perform(mockRequest)
	            .andExpect(status().isOk());
	}
	
}
