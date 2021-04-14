package com.bookkeeper.demo.repository;

import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findById(Long genreId);
    Optional<Genre> findByName(String genreName);
    Optional<Genre> findByNameIgnoreCase(String genreName);
}
