package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping(path="/api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add/")
    public Book addBook(@RequestBody Map<String, String> payload){
        System.out.println("Calling addBook");
        return bookService.addBook(payload);
    }

    @GetMapping("/{bookId}")
    public Optional getBook(@PathVariable Long bookId) {
        System.out.println("calling createBook");
        return bookService.getBook(bookId);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        System.out.println("Calling getAllBooks");
        return bookService.getAllBooks();
    }

    @PutMapping("/update/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Map<String, String> bookObject) {
        System.out.println("calling updateBook");
        return bookService.updateBook(bookId, bookObject);
    }

    @DeleteMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        System.out.println("calling from DeleteBook");
        return bookService.deleteBook(bookId);
    }

    @PostMapping("/{bookId}/favorite")
    public String favoriteBook(@PathVariable Long bookId){
        System.out.println("calling favoriteBook");
        return bookService.favoriteBook(bookId);
    }

}