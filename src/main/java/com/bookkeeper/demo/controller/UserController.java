package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.User;
import com.bookkeeper.demo.model.UserProfile;
import com.bookkeeper.demo.model.request.LoginRequest;
import com.bookkeeper.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    //http://localhost:9091/auth/users/register
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("calling createUser");
        return userService.createUser(userObject);
    }

    //http://localhost:9091/auth/users/login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("calling loginUser");
        return userService.loginUser(loginRequest);
    }

    //http://localhost:9091/auth/users/createProfile
    @PostMapping("/createProfile")
    public User createUserProfile (@RequestBody UserProfile userProfileObject){
        System.out.println("calling createUserProfile");
        return userService.createUserProfile(userProfileObject);
    }

    //http://localhost:9091/auth/users/viewProfile
    @GetMapping("/viewProfile")
    public UserProfile getUserProfile() {
        System.out.println("calling getUserProfile");
        return userService.getUserProfile();
    }

    //http://localhost:9091/auth/users/resetPassword
    @PutMapping("/resetPassword")
    public User updatePassword(@RequestBody User userObject){
        System.out.println("calling Update Password");
        return userService.updatePassword(userObject);
    }
}
