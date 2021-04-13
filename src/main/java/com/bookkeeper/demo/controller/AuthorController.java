package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Author;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author authorObject) {
        System.out.println("Calling addAuthor");
        return authorService.addAuthor(authorObject);

    }

    @GetMapping("/{authorId}")
    public Optional getAuthor(@PathVariable Long authorId) {
        System.out.println("calling createBook");
        return authorService.getAuthor(authorId);
    }

    @GetMapping
    public List<Author> getAllAuthor(){
        System.out.println("Calling getAllBooks");
        return authorService.getAllAuthor();
    }

    @DeleteMapping("/{authorId}")
    public String deleteAuthor(@PathVariable Long authorId) {
        System.out.println("calling from DeleteBook");
        return authorService.deleteAuthor(authorId);
    }
}
