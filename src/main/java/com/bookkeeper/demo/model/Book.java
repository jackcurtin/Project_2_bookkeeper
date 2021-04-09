package com.bookkeeper.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String synopsis;
    @Column
    private int pageCount;
    @Column
    private int isbn;


    public Book() {
    }

    public Book(String title, String synopsis, int pageCount, int isbn) {
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

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
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
}
