package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        System.out.println("Calling getAllBooks");
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Map<String, String> payload){
        System.out.println("Calling addBook");
        return bookService.addBook(payload);
    }

    @PostMapping("/genres")
    public Genre addGenre(@RequestBody Genre genreObject){
        System.out.println("Calling addGenre");
        return bookService.addGenre(genreObject);
    }

    @PostMapping("/test")
    public Map<String, String> test(@RequestBody Map<String, String> payload){
        System.out.println("Calling test");
        System.out.println(payload.get("genre_id").getClass());
        return payload;
    }

}
