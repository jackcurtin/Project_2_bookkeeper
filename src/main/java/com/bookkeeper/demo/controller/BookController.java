package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //http://localhost:9091/api/hello
    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    //http://localhost:9091/api/books
    @PostMapping("/books")
    public Book createBook(@RequestBody Book bookObject) {
        System.out.println("calling from createBook");
        return bookService.createBook(bookObject);
    }

    //http://localhost:9091/api/books
    @GetMapping("/books/{bookId}")
    public Optional getBook(@PathVariable Long bookId) {
        System.out.println("calling createBook");
        return bookService.getBook(bookId);
    }

    //http://localhost:9091/api/books
    @GetMapping("/books")
    public List<Book> getBooks() {
        System.out.println("calling getBooks");
        return bookService.getBooks();
    }

    //http://localhost:9091/api/books/1
    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book bookObject) {
        System.out.println("calling fon UpdateBook");
        return bookService.updateBook(bookId, bookObject);
    }

    //http://localhost:9091/api/books/1
    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        System.out.println("calling from DeleteBook");
        return bookService.deleteBook(bookId);
    }
}
