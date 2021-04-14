package com.bookkeeper.demo;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.repository.BookRepository;
import com.bookkeeper.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookkeeperApplicationTests {

    @Test
    void CheckIfBooksAreFoundSameIsReturned() {
        BookService service  = new BookService();
        BookRepository respository =  mock(BookRepository.class);
        Book a = new Book();
        Book b = new Book();
        List<Book> books = new ArrayList<Book>();
        books.add(a);
        books.add(b);
        when(respository.findAll()).thenReturn(books);
        service.setBookRepository(respository);
        List<Book> actualBooks =  service.getAllBooks();
        assertEquals(1, actualBooks.stream().count());
    }

    @Test
    void CheckIfBooksAreNotFoundThenExceptionIsThrown() {
        BookService service  = new BookService();
        BookRepository respository =  mock(BookRepository.class);
        List<Book> books = new ArrayList<>();

        when(respository.findAll()).thenReturn(books);

        service.setBookRepository(respository);

        Exception exception = assertThrows(InformationExistsException.class, () -> {
            service.getAllBooks();
        });

        assertEquals("there are no books here", exception.getMessage());
    }

}
