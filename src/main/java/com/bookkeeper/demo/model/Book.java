package com.bookkeeper.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    public List<UserProfile> getUserFavorite() {
        return userFavorite;
    }

    public void setUserFavorite(List<UserProfile> userFavorite) {
        this.userFavorite = userFavorite;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String title;
    @Column
    private String synopsis;
    @Column
    private int pageCount;
    @Column
    private Long isbn;

    @ManyToOne
    private Genre genre;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Publisher publisher;
    @ManyToMany(mappedBy = "userFavoriteBooks")
    private List<UserProfile> userFavorite;

    public Book() {
    }

    public Book(String title, String synopsis, int pageCount, Long isbn) {
        this.title = title;
        this.synopsis = synopsis;
        this.pageCount = pageCount;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", pageCount=" + pageCount +
                ", isbn=" + isbn +
                '}';
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
