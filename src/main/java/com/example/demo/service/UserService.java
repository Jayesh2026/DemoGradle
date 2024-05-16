package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ExistingUserException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.UserServiceException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveNewUser(User user) {
        if (user == null) {
            throw new NullPointerException("User can't be null");
        }
        try {
            User existingUser = userRepository.findUserByEmail(user.getEmail());
            if (existingUser != null) {
                throw new ExistingUserException("User with " + user.getEmail() + " this email already exists");
            }
            return userRepository.saveNewUser(user);
        }catch(ExistingUserException exception){
            throw exception;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(int id) {
        try {
            User user = userRepository.findUserById(id);
            if (user == null) {
                throw new UserNotFoundException("User with id: " + id + " is not present.");
            }
            User userById = userRepository.findUserById(id);
            if (userById != null) {
                return userById;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    public List<User> getAllUser() {
        try {
            return userRepository.getAllUsers();
        } catch (Exception exception) {
            throw new UserServiceException("Failed to retrieve all user from database", exception);
        }
    }

    public User updateUser(String name, String city, String email_Id){
        try{
            User existingUser = userRepository.findUserByEmail(email_Id);
            if(existingUser == null){
                throw new UserNotFoundException("User with email ID: " + email_Id + " not found.");
            }
            return userRepository.updateUserByEmail(name, city, email_Id);
        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
        
    }

    public void deleteUserById(int id) {
        try {
            User userId = userRepository.findUserById(id);
            if (userId == null) {
                throw new IllegalArgumentException("User with id: " + id + " is not present.");
            }
            userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
