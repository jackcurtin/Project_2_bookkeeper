package com.bookkeeper.demo.repository;

import com.bookkeeper.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuhtorRepository extends JpaRepository<Author, Long> {
}
