package com.example.demo.service;

import java.sql.SQLException;
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
    private User userById;
    private User updateUserByEmail;

    // public Boolean saveNewUser(User user) {
    // try {
    // if (user == null) {
    // throw new IllegalArgumentException("User can't be null");
    // }
    // User existingUser = userRepository.findUserByEmail(user.getEmail());

    // if (existingUser != null) {
    // throw new ExistingUserException("user with email id: " +
    // existingUser.getEmail() + " already exists.");
    // }
    // User saveNewUser = userRepository.saveNewUser(user);
    // if (saveNewUser != null) {
    // return Boolean.TRUE;
    // }

    // } catch (Exception exception) {
    // // exception.printStackTrace();
    // return Boolean.FALSE;
    // }
    // return Boolean.FALSE;
    // }

    public User saveNewUser(User user) {
        try {
            if (user == null) {
                throw new NullPointerException("User can't be null");
            }
            User existingUser = userRepository.findUserByEmail(user.getEmail());
            if (existingUser != null) {
                throw new ExistingUserException("User with " + user.getEmail() + " this email already exists");
            }
            return userRepository.saveNewUser(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public User getUserById(int id) {
        try {
            User user = userRepository.findUserById(id);
            if (user == null) {
                throw new UserNotFoundException("User with id: " + id + " is not present.");
            }
            userById = userRepository.findUserById(id);
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
        List<User> users;
        try {
            users = userRepository.getAllUsers();
            return users;
        } catch (SQLException exception) {
            throw new UserServiceException("Failed to retrieve all user from database", exception);
        }
    }

    public User updateUser(String name, String city, String email_Id){
        try{
            User existingUser = userRepository.findUserByEmail(email_Id);
            if(existingUser == null){
                throw new UserNotFoundException("User with email ID: " + email_Id + " not found.");
            }
            updateUserByEmail = userRepository.updateUserByEmail(name, city, email_Id);
            return updateUserByEmail;
        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
        
    }

    public void deleteUserById(int id) {
        try {
            User user = userRepository.findUserById(id);
            if (user == null) {
                throw new IllegalArgumentException("User with id: " + id + " is not present.");
            }
            userRepository.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
