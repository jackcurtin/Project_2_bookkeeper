package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.model.User;
import com.bookkeeper.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findUserByUserName(String userName){
        return userRepository.findByUserName(userName).get();
    }

    public User createUser(User userObject){
        System.out.println("service calling createUser");
        Optional<User> user = userRepository.findByUserName(userObject.getUserName());
        if (user.isPresent()){
            throw new InformationExistsException("User "+userObject.getUserName()+" already exist");
        } else {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }
    }

    


}
