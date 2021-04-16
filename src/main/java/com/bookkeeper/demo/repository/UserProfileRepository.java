package com.bookkeeper.demo.repository;

import com.bookkeeper.demo.model.Book;
import com.bookkeeper.demo.model.User;
import com.bookkeeper.demo.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findUserProfileByUser(User user);
    UserProfile findUserProfileById(Long id);
    List<Book> findAllByUserFavoriteBooksIn(List<Book> userFavorites);
}
