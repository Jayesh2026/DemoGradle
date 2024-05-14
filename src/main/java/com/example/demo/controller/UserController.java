package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService usersService;

    @PostMapping("/saveUser")
    public User saveNewUser(@RequestBody User user) {
        return usersService.saveNewUser(user);
    }

    @GetMapping("/getUser-byId/{id}")
    public User getUserById(@PathVariable int id) {
        return usersService.getUserById(id);
    }

    @GetMapping("/get-allUsers")
    public List<User> getAllUser() {
        List<User> userList = usersService.getAllUser();
        return userList;
    }

    @PutMapping("update-user")
    public User updateUser(@RequestParam("id") int id, @RequestParam("name") String name,
            @RequestParam("city") String city) {
        return usersService.updateUser(id,name,city);
    }

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestParam("id") int id) {
        usersService.deleteUserById(id);
    }
    
}
