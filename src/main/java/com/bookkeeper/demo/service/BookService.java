package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository (BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        System.out.println("Service is calling getAllBooks");
        List<Book> allBooks = bookRepository.findAll();
        if(allBooks.isEmpty()){
            throw new InformationExistsException("there are no books here");
        }
        return allBooks;
    }

    public Book addBook(Book bookObject){
        System.out.println("Service is calling addBook");
        Book bookChecker = bookRepository.findByTitle(bookObject.getTitle());
        if(bookChecker != null){
            throw new InformationExistsException("Book with title " + bookChecker.getTitle()
                    + " already exists in this database");
        } else{
            return bookRepository.save(bookObject);
        }

    }
}
