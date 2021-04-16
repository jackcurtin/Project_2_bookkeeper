package com.bookkeeper.demo.service;

import com.bookkeeper.demo.controller.AuthorController;
import com.bookkeeper.demo.exception.CannotBeNullException;
import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.Author;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorController authorController;
    private AuthorRepository authorRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setAuthorController(AuthorController authorController) {
        this.authorController = authorController;
    }

    public Author addAuthor(Author authorObject) {
        System.out.println("Service Calling addAuthor");
        Optional author = authorRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(authorObject.getFirstName(), authorObject.getLastName());
        if (author.isPresent()){
            throw new InformationExistsException("Author "+authorObject.getFirstName() + " "+ authorObject.getLastName()+" already present");
        } else {
            if(authorObject.getFirstName().length()<1 && authorObject.getLastName().length() <1){
                throw new CannotBeNullException("Author firstName or last name cannot be null");
            } else {
                return authorRepository.save(authorObject);
            }
        }
    }

    public Optional getAuthor(Long authorId) {
        System.out.println("service Calling createAuthor");
        Optional author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            return author;
        } else {
            throw new InformationNotFoundException("Author with id" + authorId + "not found");
        }
    }

    public List<Author> getAllAuthor(){
        System.out.println("Service is calling getAllAuthor");
        List<Author> allAuthor = authorRepository.findAll();
        if(allAuthor.isEmpty()){
            throw new InformationExistsException("there are no Author here");
        }
        return allAuthor;
    }

    public String deleteAuthor(Long authorId) {
        System.out.println("service calling deleteAuthor");
        Optional<Author> author = authorRepository.findById(authorId);
        if ( author.isPresent()) {
            authorRepository.deleteById(authorId);
            return "Author " + authorId + " deleted";
        } else {
            throw new InformationNotFoundException("Author with id " + authorId + "not found");
        }
    }

    public List<Book> getAllBooksByAuthor(Long authorId) {
        System.out.println("service calling getAllBooksByAuthor");
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()){
            return author.get().getBookList();
        }else{
            throw new InformationNotFoundException("Book with id " + authorId + "not found");
        }
    }
}
