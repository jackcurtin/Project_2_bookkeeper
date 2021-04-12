package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.repository.GenreRepository;
import javax.management.InstanceNotFoundException;
import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private GenreRepository genreRepository;

    @Autowired
    public void setBookRepository (BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setGenreRepository (GenreRepository genreRepository){
        this.genreRepository = genreRepository;
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

    public List<Book> getAllBooks(){
        System.out.println("Service is calling getAllBooks");
        List<Book> allBooks = bookRepository.findAll();
        if(allBooks.isEmpty()){
            throw new InformationExistsException("there are no books here");
        }
        return allBooks;
    }

    public Book addBook(Map<String, String> bookObject){
        System.out.println("Service is calling addBook");
        Book bookChecker = bookRepository.findByTitle(bookObject.get("title"));
        if(bookChecker != null){
            throw new InformationExistsException("Book with title " + bookChecker.getTitle()
                    + " already exists in this database");
        } else{
            Optional<Genre> genreChecker = genreRepository.findByName(bookObject.get("genre_name"));
            if(genreChecker.isEmpty()){
                throw new InformationNotFoundException("No genre with name " + bookObject.get("genre_name") +
                        " found in the database");
            } else{
                Book book = new Book();
                book.setTitle(bookObject.get("title"));
                book.setSynopsis(bookObject.get("synopsis"));
                book.setPageCount(Integer.parseInt(bookObject.get("pageCount")));
                book.setIsbn(Long.valueOf(bookObject.get("isbn")));
                book.setGenre(genreChecker.get());
                return bookRepository.save(book);

            }
        }
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
