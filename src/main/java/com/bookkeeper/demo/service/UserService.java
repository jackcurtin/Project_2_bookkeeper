package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.User;
import com.bookkeeper.demo.model.request.LoginRequest;
import com.bookkeeper.demo.model.response.LoginResponse;
import com.bookkeeper.demo.repository.UserRepository;
import com.bookkeeper.demo.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

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

    public ResponseEntity<Object> loginUser(LoginRequest loginRequest) {
        System.out.println("service calling loginUser ==>");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
            final String JWT = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));

        }
        catch(NullPointerException e){
            System.out.println("fail");
            throw new InformationNotFoundException(("user with that user name"+loginRequest.getUserName()+"not found"));
        }
    }

    public User updatePassword(User userObject){
        System.out.println("service calling update userPassword");
        Optional<User> user = userRepository.findByUserName(userObject.getUserName());
        if(user.isPresent()){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationNotFoundException("User "+userObject.getUserName() +" did not exist");
        }
    }



}
