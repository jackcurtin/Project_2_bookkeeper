package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.User;
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

    @PutMapping("/resetPassword")
    public User updatePassword(@RequestBody User userObject){
        System.out.println("calling Update Password");
        return userService.updatePassword(userObject);
    }
}
