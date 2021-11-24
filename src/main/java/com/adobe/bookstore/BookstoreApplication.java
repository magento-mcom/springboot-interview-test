package com.adobe.bookstore;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.adobe.bookstore.adapter.persistence.h2.BookstorePersistenceAdapter;
import com.adobe.bookstore.domain.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	// Load initial data into database
	
	@Bean
	public CommandLineRunner runner(BookstorePersistenceAdapter bookstorePersistenceAdapter){
	    return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/stock.json");
			try {
				List<Book> books = mapper.readValue(inputStream, typeReference);
				bookstorePersistenceAdapter.save(books);
				System.out.println("Books have been saved.");
			} catch (IOException e){
				System.out.println("Unable to save books: " + e.getMessage());
			}
	    };
	}

}
