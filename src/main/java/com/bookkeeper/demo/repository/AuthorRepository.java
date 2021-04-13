package com.bookkeeper.demo.repository;

import com.bookkeeper.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
   Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
   Optional<Author> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String first, String lastName);
}
