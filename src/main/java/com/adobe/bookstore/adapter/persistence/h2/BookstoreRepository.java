package com.adobe.bookstore.adapter.persistence.h2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adobe.bookstore.domain.Book;

@Repository
public interface BookstoreRepository extends JpaRepository<Book, UUID> {

}
