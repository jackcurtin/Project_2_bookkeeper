package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping
    public Genre addGenre(@RequestBody Genre genreObject){
        System.out.println("Calling addGenre");
        return genreService.addGenre(genreObject);
    }
}
