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
@RequestMapping(path="/api")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //http://localhost:PORTNUMBER/api/hello
    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Map<String, String> payload){
        System.out.println("Calling addBook");
        return bookService.addBook(payload);
    }

    //http://localhost:PORTNUMBER/api/books
    @GetMapping("/books/{bookId}")
    public Optional getBook(@PathVariable Long bookId) {
        System.out.println("calling createBook");
        return bookService.getBook(bookId);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        System.out.println("Calling getAllBooks");
        return bookService.getAllBooks();
    }

    //http://localhost:PORTNUMBER/api/books/1
    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book bookObject) {
        System.out.println("calling fon UpdateBook");
        return bookService.updateBook(bookId, bookObject);
    }

    //http://localhost:PORTNUMBER/api/books/1
    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        System.out.println("calling from DeleteBook");
        return bookService.deleteBook(bookId);
    }

    @PostMapping("/test")
    public Map<String, String> test(@RequestBody Map<String, String> payload){
        System.out.println("Calling test");
        System.out.println(payload.get("genre_id").getClass());
        return payload;
    }
}
