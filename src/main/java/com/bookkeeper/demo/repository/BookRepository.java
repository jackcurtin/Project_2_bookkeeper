package com.bookkeeper.demo.repository;

import com.bookkeeper.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Long> {
    Optional<Book> findByTitle(String bookTitle);
}
