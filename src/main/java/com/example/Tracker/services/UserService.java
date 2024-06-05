package com.example.Tracker.services;

import com.example.Tracker.model.User;
import com.example.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        user.setEncryPassword(passwordEncoder.encode(user.getEncryPassword()));
        return userRepository.save(user);
    }
}
