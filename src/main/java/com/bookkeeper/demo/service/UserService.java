package com.bookkeeper.demo.service;

import com.bookkeeper.demo.model.User;
import com.bookkeeper.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }



}
