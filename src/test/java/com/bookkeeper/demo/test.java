package com.bookkeeper.demo;

import com.bookkeeper.demo.model.Author;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.model.Publisher;
import com.bookkeeper.demo.repository.AuthorRepository;
import com.bookkeeper.demo.repository.BookRepository;
import com.bookkeeper.demo.repository.GenreRepository;
import com.bookkeeper.demo.repository.PublisherRepository;
import com.bookkeeper.demo.service.AuthorService;
import com.bookkeeper.demo.service.BookService;
import com.bookkeeper.demo.service.GenreService;
import com.bookkeeper.demo.service.PublisherService;
import org.hamcrest.collection.IsMapContaining;


import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class test {

    private Book book1;
    private Book book2;
    private Book book3;

    @Mock
    BookRepository bookRepositoryMock;

    @Mock
    GenreRepository genreRepositoryMock;

    @Mock
    AuthorRepository authorRepositoryMock;

    @Mock
    PublisherRepository publisherRepositoryMock;

    @InjectMocks
    BookService bookService;

    @InjectMocks
    GenreService genreService;

    @InjectMocks
    AuthorService authorService;

    @InjectMocks
    PublisherService publisherService;

    @Test
    public void testFindAllBooks(){
        List<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        when(bookRepositoryMock.findAll()).thenReturn(books);
        assertEquals(3, bookService.getAllBooks().size());
    }

//    @Test
//    public void testAddBook(){
//        Map <String, String> bookObject = new HashMap<String, String>();
//        bookObject.put("title", "bookTitle");
//        bookObject.put("synopsis", "very large");
//        bookObject.put("pageCount", "40000");
//        bookObject.put("isbn", "10002349902");
//        bookObject.put("genre_name", "history");
//        bookObject.put("author_first_name", "William");
//        bookObject.put("author_last_name","Gibson");
//        bookObject.put("publisher_name","Reading Place");
//        Genre genre = new Genre("history", "happened");
//        Author author = new Author("William", "Gibson", 70, "usa");
//        Publisher publisher = new Publisher("Reading Place", "123 Main St");
//        genreService.addGenre(genre);
//        authorService.addAuthor(author);
//        publisherService.addPublisher(publisher);
//
//        System.out.println(genreRepositoryMock.findAll());
//
//        assertThat(bookObject.size(), is(8));
//        assertThat(bookObject, not(IsMapContaining.hasEntry("age", "Gibson")));
//        assertThat(bookObject, IsMapContaining.hasKey("isbn"));
//        when(bookRepositoryMock.findByTitleIgnoreCase(bookObject.get("title"))).thenReturn(null);
//        when(genreRepositoryMock.findByName(bookObject.get("genre_name"))).thenReturn(genreRepositoryMock.findByName(bookObject.get("genre_name")));
//        when(authorRepositoryMock.findByFirstNameAndLastName(bookObject.get("author_first_name"), bookObject.get("author_last_name")))
//                .thenReturn(Optional.of(author));
//        when(publisherRepositoryMock.findByName(bookObject.get("publisher_name"))).thenReturn(Optional.of(publisher));
//        Book actual = bookService.addBook(bookObject);
//        assertEquals(bookObject, IsMapContaining.hasValue(actual.getIsbn()));
//    }
}
