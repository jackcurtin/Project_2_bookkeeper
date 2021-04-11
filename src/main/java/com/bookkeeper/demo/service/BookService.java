package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
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
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book bookObject) {
        System.out.println("Service Calling CreateBook");
        Book book = bookRepository.findByTitle(bookObject.getTitle());
        if (book == null) {
            return bookRepository.save(bookObject);
        } else {
            throw new InformationExistsException("Book with this title " + bookObject.getTitle() + "already exist");
        }
    }

    public Optional getBook(Long bookId) {
        System.out.println("service Calling createBook");
        Optional book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new InformationNotFoundException("Book with id" + bookId + "not found");
        }
    }

    public List<Book> getBooks() {
        System.out.println("Service calling getBooks");
        return bookRepository.findAll();
    }

    public Book updateBook(Long bookId, Book bookObject) {
        System.out.println("service calling update Book");
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            if (bookObject.getIsbn() == (book.get().getIsbn())) {
                System.out.println("same book");
                throw new InformationExistsException("book of this " + book.get().getTitle() + "already exist");
            } else {
                bookObject.setTitle(bookObject.getTitle());
                bookObject.setSynopsis(bookObject.getSynopsis());
                bookObject.setPageCount(bookObject.getPageCount());
                return bookRepository.save(bookObject);
            }
        } else {
            throw new InformationNotFoundException("Book with id " + bookId + "not found");
        }
    }

    public String deleteBook(Long bookId) {
        System.out.println("service calling deleteBook");
        Optional<Book> book = bookRepository.findById(bookId);
        if ( book.isPresent()) {
            bookRepository.deleteById(bookId);
            return "book " + bookId + " deleted";
        } else {
            throw new InformationNotFoundException("Book with id " + bookId + "not found");
        }
    }

}
