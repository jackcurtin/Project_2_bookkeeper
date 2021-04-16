package com.bookkeeper.demo.model;

import com.bookkeeper.demo.compositeKey.UserRatingKey;
import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.UserProfile;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserRating {

    @EmbeddedId
    UserRatingKey id;

    @ManyToOne
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    UserProfile userProfile;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    Book book;

    int rating;

    public UserRating() {
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
