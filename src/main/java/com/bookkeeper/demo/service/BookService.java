package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.repository.BookRepository;
import com.bookkeeper.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import javax.swing.text.html.Option;
import java.util.List;
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

    public Genre addGenre(Genre genreObject){
        System.out.println("Service is calling addGenre");
        Optional <Genre> genreChecker = genreRepository.findByName(genreObject.getName());
        if (genreChecker.isPresent()){
            throw new InformationExistsException("Genre with name " + genreObject.getName()
                    + " already exists in this database");
        } else{
            return genreRepository.save(genreObject);
        }
    }
}
