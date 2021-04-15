package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.CannotBeNullException;
import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.Author;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.Publisher;
import com.bookkeeper.demo.repository.AuthorRepository;
import com.bookkeeper.demo.repository.BookRepository;
import com.bookkeeper.demo.repository.PublisherRepository;
import com.bookkeeper.demo.security.JWTUtils;
import com.bookkeeper.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;

    @Autowired
    public void setBookRepository (BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setGenreRepository (GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setPublisherRepository(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
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
        Optional<Book> bookChecker = bookRepository.findByTitle(bookObject.get("title"));
        if(bookChecker.isPresent() && bookChecker.get().getTitle().equalsIgnoreCase(bookObject.get("title"))){
            throw new InformationExistsException("Book with title " + bookChecker.get().getTitle()
                    + " already exists in this database");
        } else{
            Book book = new Book();
            return bookCreateOrUpdates(book, bookObject);
        }
    }

    public Book updateBook(Long bookId, Map<String, String> bookObject) {
        System.out.println("service calling update Book");
        Optional<Book> bookChecker = bookRepository.findById(bookId);
        if (bookChecker.isPresent()) {
            if (bookRepository.findByTitleIgnoreCase(bookObject.get("title")).isPresent()) {
                throw new InformationExistsException("book titled " + bookObject.get("title")
                        + " already exists in the database");
            } else {
                Book book = bookChecker.get();
                return bookCreateOrUpdates(book, bookObject);
          }
        } else {
            throw new InformationNotFoundException("Book with ID " + bookId + " not found in the database");
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

    private Book bookCreateOrUpdates (Book book, Map <String, String> bookObject){
        Optional<Genre> genreChecker = genreRepository.findByNameIgnoreCase(bookObject.get("genre_name"));
        Optional<Author> authorChecker = authorRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase
                (bookObject.get("author_first_name"), bookObject.get("author_last_name"));
        Optional<Publisher> publisherChecker = publisherRepository.findByNameIgnoreCase(bookObject.get("publisher_name"));
        if (genreChecker.isEmpty()) {
            throw new InformationNotFoundException("No genre with name " + bookObject.get("genre_name") +
                    " found in the database");
        } else if (authorChecker.isEmpty()) {
            throw new InformationNotFoundException("No author with name " + bookObject.get("author_first_name") + " " + bookObject.get("author_last_name") +
                    " found in the database");
        } else if (publisherChecker.isEmpty()) {
            throw new InformationNotFoundException("No publisher with name " + bookObject.get("publisher_name") +
                    " found in the database");
        } else {
            if (bookObject.get("title").length() < 1){
                throw new CannotBeNullException("Title cannot be left empty");
            }else {
                book.setTitle(bookObject.get("title"));
                book.setSynopsis(bookObject.get("synopsis"));
                book.setPageCount(Integer.parseInt(bookObject.get("pageCount")));
                book.setIsbn(Long.valueOf(bookObject.get("isbn")));
                book.setGenre(genreChecker.get());
                book.setAuthor(authorChecker.get());
                book.setPublisher(publisherChecker.get());
                return bookRepository.save(book);
            }
        }
    }
}
