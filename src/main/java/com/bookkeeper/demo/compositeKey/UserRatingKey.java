package com.bookkeeper.demo.compositeKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRatingKey implements Serializable {

    @Column(name = "profile_id")
    Long userId;

    @Column(name = "book_id")
    Long bookId;
}
