package com.example.Tracker.services;

import com.example.Tracker.model.User;
import com.example.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User getUser(String username){
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new RuntimeException("User not found!!");
    }

    public User getUserById(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new RuntimeException("User not found!!");
    }
}
