package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
