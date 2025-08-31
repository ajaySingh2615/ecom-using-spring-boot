package com.cadt.ecomproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cadt.ecomproject.model.User;
import com.cadt.ecomproject.repo.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user: " + e.getMessage());
        }
    }

    public User loginUser(String email, String password) {
        try {
            // check if user exists
            User user = userRepository.findByEmail(email);
            if(user!=null && user.getPassword().equals(password)){
                return user;
            }else{
                throw new RuntimeException("Invalid email or password");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to login user: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all users: " + e.getMessage());
        }
    }

}
