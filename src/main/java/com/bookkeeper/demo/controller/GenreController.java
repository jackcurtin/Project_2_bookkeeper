package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private GenreService genreService;

    @Autowired
    public void setGenreService (GenreService genreService){
        this.genreService = genreService;
    }

    //http://localhost:9091/api/genres
    @GetMapping
    public List<Genre> getAllGenres(){
        System.out.println("calling getAllGenres");
        return genreService.getAllGenres();
    }

    //http://localhost:9091/api/genres/1
    @GetMapping("/{genreId}")
    public Genre getGenre(@PathVariable Long genreId){
        System.out.println("calling getGenre");
        return genreService.getGenre(genreId);
    }

    //http://localhost:9091/api/genres/add/
    @PostMapping("/add/")
    public Genre addGenre(@RequestBody Genre genreObject){
        System.out.println("Calling addGenre");
        return genreService.addGenre(genreObject);
    }

    //http://localhost:9091/api/genres/delete/1
    @DeleteMapping("/delete/{genreId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long genreId){
        System.out.println("Calling deleteGenre");
        return genreService.deleteGenre(genreId);
    }
    //http://localhost:9091/api/genres/1/allBooks
    @GetMapping("/{genreId}/allBooks")
    public List<Book> getAllBooksByGenre(@PathVariable Long genreId){
        System.out.println("calling getAllBooksByGenre");
        return genreService.getAllBooksByGenre(genreId);
    }
}
