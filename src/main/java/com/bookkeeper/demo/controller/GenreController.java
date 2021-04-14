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

    @GetMapping
    public List<Genre> getAllGenres(){
        System.out.println("calling getAllGenres");
        return genreService.getAllGenres();
    }

    @GetMapping("/{genreId}")
    public Genre getGenre(@PathVariable Long genreId){
        System.out.println("calling getGenre");
        return genreService.getGenre(genreId);
    }

    @PostMapping("/add/")
    public Genre addGenre(@RequestBody Genre genreObject){
        System.out.println("Calling addGenre");
        return genreService.addGenre(genreObject);
    }

    @DeleteMapping("/delete/{genreId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long genreId){
        System.out.println("Calling deleteGenre");
        return genreService.deleteGenre(genreId);
    }
}
