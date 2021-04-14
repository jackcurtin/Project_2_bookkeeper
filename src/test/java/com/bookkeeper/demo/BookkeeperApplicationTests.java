//package com.bookkeeper.demo;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.bookkeeper.demo.model.Book;
//import com.bookkeeper.demo.repository.BookRepository;
//import com.bookkeeper.demo.service.BookService;
//import org.hamcrest.collection.IsMapContaining;
//
//
//import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import javax.swing.*;
//import java.util.*;
//
//@SpringBootTest
//class BookkeeperApplicationTests {
//
//    @Test
//    void contextLoads() {
//        private Book book1;
//        private Book book2;
//        private Book book3;
//
//        @Mock
//        BookRepository bookRepositoryMock;
//
//        @InjectMocks
//        BookService bookService;
//
//        @org.junit.Test
//        public void testFindAllBooks(){
//            List<Book> books = new ArrayList<Book>();
//            books.add(book1);
//            books.add(book2);
//            books.add(book3);
//            when(bookRepositoryMock.findAll()).thenReturn(books);
//            assertEquals(3, bookService.getAllBooks().size());
//        }
//
//        @org.junit.Test
//        public void testAddBook(){
//            Map <String, String> bookObject = new HashMap<String, String>();
//            bookObject.put("title", "bookTitle");
//            bookObject.put("synopsis", "very large");
//            bookObject.put("pageCount", "40000");
//            bookObject.put("isbn", "10002349902");
//            bookObject.put("genre_name", "history");
//            bookObject.put("author_first_name", "William");
//            bookObject.put("author_last_name","Gibson");
//            bookObject.put("publisher_name","Reading Place");
//            assertThat(bookObject.size(), is(8));
//            assertThat(bookObject, not(IsMapContaining.hasEntry("age", "Gibson")));
//            assertThat(bookObject, IsMapContaining.hasKey("isbn"));
//            //Map mockAvailableActions =mock(Map.class);
//            //when().thenReturn((Set) bookObject);
//            //assertEquals(, bookService.addBook(bookObject));
////        when(bookRepositoryMock.findByTitleIgnoreCase(bookObject.get("title")).equals(null)).thenReturn(bookObject);
//        }
//
//    }
//
//}
