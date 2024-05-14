package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public User saveNewUser(User user){
        return userRepository.saveNewUser(user);
    }

     public User getUserById(int id){
        return userRepository.getUserById(id);
    }

    public List<User> getAllUser(){
        List<User> users = userRepository.getAllUsers();
        return users;
    }

    public User updateUser(int id,String name,String city){
        return userRepository.updateUser(id,name,city);
    }

    public void deleteUserById(int id){
        userRepository.deleted(id);
    }
    
}
