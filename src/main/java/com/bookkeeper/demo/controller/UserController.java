package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.User;
import com.bookkeeper.demo.model.UserProfile;
import com.bookkeeper.demo.model.request.LoginRequest;
import com.bookkeeper.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("calling createUser");
        return userService.createUser(userObject);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("calling loginUser");
        return userService.loginUser(loginRequest);
    }

    @PostMapping("/createProfile")
    public User createUserProfile (@RequestBody UserProfile userProfileObject){
        System.out.println("calling createUserProfile");
        return userService.createUserProfile(userProfileObject);
    }
}
