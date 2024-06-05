package com.example.Tracker.controller;

import com.example.Tracker.model.User;
import com.example.Tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/apps")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getHome(){
        return "Home";
    }

    @GetMapping("/users/me")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> listOfUser = userService.getAllUser();
        return new ResponseEntity<>(listOfUser, HttpStatus.OK);
    }

}
