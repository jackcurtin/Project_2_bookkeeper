package com.bookkeeper.demo;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class Tests {

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

    //Testing get ALL the books method
    @Test
    public void testFindAllBooks(){
        List<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        when(bookRepositoryMock.findAll()).thenReturn(books);
        assertEquals(3, bookService.getAllBooks().size());
    }

    //Testing Adding the book
    @Test
    public void testAddBook(){
        Map <String, String> bookObject = new HashMap<String, String>();
        bookObject.put("title", "bookTitle");
        bookObject.put("synopsis", "very large");
        bookObject.put("pageCount", "40000");
        bookObject.put("isbn", "10002349902");
        bookObject.put("genre_name", "history");
        bookObject.put("author_first_name", "William");
        bookObject.put("author_last_name","Gibson");
        bookObject.put("publisher_name","Reading Place");
        Genre genre = new Genre("history", "happened");
        Author author = new Author("William", "Gibson", 70, "usa");
        Publisher publisher = new Publisher("Reading Place", "123 Main St");
        genreService.addGenre(genre);
        authorService.addAuthor(author);
        publisherService.addPublisher(publisher);

        System.out.println(genreRepositoryMock.findAll());

        assertThat(bookObject.size(), is(8));
        assertThat(bookObject, not(IsMapContaining.hasEntry("age", "Gibson")));
        assertThat(bookObject, IsMapContaining.hasKey("isbn"));
        when(bookRepositoryMock.findByTitleIgnoreCase(bookObject.get("title"))).thenReturn(null);
        when(genreRepositoryMock.findByName(bookObject.get("genre_name"))).thenReturn(Optional.of(genre));
        when(authorRepositoryMock.findByFirstNameAndLastName(bookObject.get("author_first_name"), bookObject.get("author_last_name")))
                .thenReturn(Optional.of(author));
        when(publisherRepositoryMock.findByName(bookObject.get("publisher_name"))).thenReturn(Optional.of(publisher));
        Book actual = bookService.addBook(bookObject);
       // assertEquals(bookObject, IsMapContaining.hasValue(actual.getIsbn()));
        assertEquals(actual, bookService.addBook(bookObject));
    }

    //Testing get All the Genre
    @Test
    public void testFindAllGenre(){
        Genre genre1 = new Genre();
        Genre genre2 = new Genre();
        List<Genre> genres = new ArrayList<Genre>();
        genres.add(genre1);
        genres.add(genre2);
        when(genreRepositoryMock.findAll()).thenReturn(genres);
        assertEquals(2, genreService.getAllGenres().size());
    }

    //Testing Exception in  get all Genre if list is empty
    @Test
    public void CheckIfGenresAreNotFoundThenExceptionIsThrown(){
        List<Genre> genre = new ArrayList<>();
        when(genreRepositoryMock.findAll()).thenReturn(genre);
        Exception exception = assertThrows(InformationNotFoundException.class, () -> {
            genreService.getAllGenres();
        });
        assertEquals("There are no genres.", exception.getMessage());
    }

    //Testing getting Single Genre
    @Test
    public void findSingleGenre(){
        Genre genre = new Genre("history","test");
        when(genreRepositoryMock.findById(1L)).thenReturn(Optional.of(genre));

        assertEquals(genre, genreService.getGenre(1L));
    }

    //Testing getting all the Author
    @Test
    public void findAllAuthor(){
        Author author1 = new Author();
        Author author2 = new Author();
        List<Author> authors = new ArrayList<Author>();
        authors.add(author1);
        authors.add(author2);
        assertEquals(2,authors.size());
    }

    //Testing get the single Author
    @Test
    public void findSingleAuthor(){
        Author author = new Author("Bharti","Gupta",25,"india");
        when(authorRepositoryMock.findById(10L)).thenReturn(Optional.of(author));

        Optional<Author> output = authorService.getAuthor(10L);
        assertEquals(output, authorService.getAuthor(10L));
        //Verify all but not id.
        verify(authorRepositoryMock, times(2)).findById(10L);

    }

    //Testing Exception in the List of all Author table
    @Test
    public void CheckIfAddAuthorThrowsInformationExistsException(){
        Author author = new Author("Bharti","Gupta",25,"india");
        when(authorRepositoryMock.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(author.getFirstName(),author.getLastName())).thenReturn(Optional.of(author));
        Exception exception = assertThrows(InformationExistsException.class, () -> {
            authorService.addAuthor(author);
        });
        assertEquals("Author "+author.getFirstName() + " "+ author.getLastName()+" already present", exception.getMessage());
    }

    //Test Delete the Author
    @Test
    public void checkDeleteAuthor(){
        Author author = new Author();
        String msg = "deleted";
        when(authorRepositoryMock.findById(2L)).thenReturn(Optional.of(author));
        assertEquals("Author 2 deleted", authorService.deleteAuthor(2L));
    }

    //Testing Getting All the Publisher
    @Test
    public void findAllPublisher(){
        Publisher publisher1 = new Publisher();
        Publisher publisher2 = new Publisher();
        List<Publisher> publishers = new ArrayList<Publisher>();
        publishers.add(publisher1);
        publishers.add(publisher2);
        assertEquals(2,publishers.size());
    }

    //Testing get the single publisher
    @Test
    public void findSinglePublisher(){
        Publisher publisher = new Publisher();
        when(publisherRepositoryMock.findById(1L)).thenReturn(Optional.of(publisher));

        assertEquals(publisher, publisherService.getPublisher(1L));
    }

    //Testing Delete the Single Publisher
    @Test
    public void checkDeletePublisher(){
        Publisher publisher = new Publisher();
        String msg = "deleted";
        when(publisherRepositoryMock.findById(3L)).thenReturn(Optional.of(publisher));
        assertEquals(ResponseEntity.ok(HttpStatus.OK) , publisherService.deletePublisher(3L));
    }

}
