package com.example.Tracker.controller;

import com.example.Tracker.model.JwtRequest;
import com.example.Tracker.model.JwtResponse;
import com.example.Tracker.model.User;
import com.example.Tracker.security.JwtHelper;
import com.example.Tracker.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    private ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){

        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtHelper.generateToken(userDetails);

        JwtResponse response = new JwtResponse(token,userDetails.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    private ResponseEntity<User> signup(@RequestBody User user){
        User responseUser = userService.saveUser(user);
        return new ResponseEntity<>(responseUser,HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);

        try{
            manager.authenticate(authentication);
        }
        catch (BadCredentialsException e){
            throw new RuntimeException(" Invalid Username or Password !!");
        }
    }


}
