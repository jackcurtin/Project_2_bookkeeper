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

    //http://localhost:9091/api/books/add/
    @PostMapping("/add/")
    public Book addBook(@RequestBody Map<String, String> payload){
        System.out.println("Calling addBook");
        return bookService.addBook(payload);
    }

    //http://localhost:9091/api/books/1
    @GetMapping("/{bookId}")
    public Optional getBook(@PathVariable Long bookId) {
        System.out.println("calling createBook");
        return bookService.getBook(bookId);
    }

    //http://localhost:9091/api/books
    @GetMapping
    public List<Book> getAllBooks(){
        System.out.println("Calling getAllBooks");
        return bookService.getAllBooks();
    }

    //http://localhost:9091/api/books/update/1
    @PutMapping("/update/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Map<String, String> bookObject) {
        System.out.println("calling updateBook");
        return bookService.updateBook(bookId, bookObject);
    }

    //http://localhost:9091/api/books/delete/5
    @DeleteMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        System.out.println("calling from DeleteBook");
        return bookService.deleteBook(bookId);
    }

    //http://localhost:9091/api/books/5/favorite
    @PostMapping("/{bookId}/favorite")
    public String favoriteBook(@PathVariable Long bookId){
        System.out.println("calling favoriteBook");
        return bookService.favoriteBook(bookId);
    }

}