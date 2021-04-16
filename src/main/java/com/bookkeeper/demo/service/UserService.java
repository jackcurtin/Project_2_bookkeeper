package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.User;
import com.bookkeeper.demo.model.UserProfile;
import com.bookkeeper.demo.model.request.LoginRequest;
import com.bookkeeper.demo.model.response.LoginResponse;
import com.bookkeeper.demo.repository.UserProfileRepository;
import com.bookkeeper.demo.repository.UserRepository;
import com.bookkeeper.demo.security.JWTUtils;
import com.bookkeeper.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserProfileRepository(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    public User findUserByUserName(String userName){

        return userRepository.findByUserName(userName).get();
    }

    //Create the User
    public User createUser(User userObject) {
        System.out.println("service calling createUser");
        Optional<User> user = userRepository.findByUserName(userObject.getUserName());
        if (user.isPresent()) {
            throw new InformationExistsException("User " + userObject.getUserName() + " already exist");
        } else {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }
    }

    //Login User
    public ResponseEntity<Object> loginUser(LoginRequest loginRequest) {
        System.out.println("service calling loginUser ==>");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
            final String JWT = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        }catch(NullPointerException e){
            throw new InformationNotFoundException(("user with that user name"+loginRequest.getUserName()+"not found"));
        }
    }

    //Create the User Profile
    public User createUserProfile(UserProfile userProfileObject){
        System.out.println("service calling createUserProfile");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUserName(userDetails.getUsername()).get();
        userProfileObject.setUser(user);
        userProfileRepository.save(userProfileObject);
        System.out.println("user profile object id " + userProfileObject.getId());
        user.setUserProfile(userProfileRepository.findUserProfileById(userProfileObject.getId()));
        return userRepository.save(user);
    }

    //Get the User Profile
    public UserProfile getUserProfile () {
        System.out.println("Service calling getUserProfile");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUserName(userDetails.getUsername()).get();
        return userProfileRepository.findUserProfileById(user.getUserProfile().getId());
    }


    //Update the Password of user
    public User updatePassword(User userObject) {
        System.out.println("service calling update userPassword");
        Optional<User> user = userRepository.findByUserName(userObject.getUserName());
        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(user.get());
        } else {
            throw new InformationNotFoundException("User " + userObject.getUserName() + " did not exist");
        }
    }
}
