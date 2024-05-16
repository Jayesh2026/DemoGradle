package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.Response;
import com.example.demo.exception.UserServiceException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService usersService;

    @PostMapping("/saveUser")
    public ResponseEntity<Response> saveNewUser(@RequestBody User user) {
        try {
            User savedUser = usersService.saveNewUser(user);
            if (savedUser != null) {
                Response response = new Response();
                response.setMessage("User saved successfully");
                response.setStatusCode(201); // user account created
                response.setData(savedUser);
                return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
            } else {
                Response response = new Response();
                response.setMessage("Error: User already exits.");
                response.setStatusCode(409); // conflict duplicate data
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Response response = new Response();
            response.setMessage("Error while saving user");
            response.setStatusCode(400); // bad request
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        }
    }

    @GetMapping("/getUser-byId/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable int id) {
        try{
            User user = usersService.getUserById(id);
            if(user != null){
                Response response = new Response();
                response.setMessage("User retrieve successfully.");
                response.setStatusCode(200); // status ok
                response.setData(user);
                return new ResponseEntity<Response>(response, HttpStatus.valueOf(response.getStatusCode()));
            }else{
                Response response = new Response();
                response.setMessage("User not able to retrieve.");
                response.setStatusCode(404); //not found
                response.setData(user);
                return new ResponseEntity<Response>(response, HttpStatus.valueOf(response.getStatusCode()));
            }
        } catch(UserServiceException exception){
            Response response = new Response();
            response.setMessage("Failed to retrieve user data of id: "+id);
            response.setStatusCode(500);
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception exception) {
            exception.printStackTrace();
            Response response = new Response();
            response.setMessage("Error while retrieving user.");
            response.setStatusCode(400);  // bad request
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        }

    }

    @GetMapping("/get-allUsers")
    public ResponseEntity<Response> getAllUser() {
        try {
            List<User> userList = usersService.getAllUser();
            Response response = new Response();
            response.setMessage("All users data retrieved successfully");
            response.setStatusCode(200);  // status ok
            response.setData(userList);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (UserServiceException exception) {
            exception.printStackTrace();
            Response response = new Response();
            response.setMessage("Failed to retrieve all users from database");
            response.setStatusCode(500);  // Internal server error
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception exception) {
            exception.printStackTrace();
            Response response = new Response();
            response.setMessage("Error while retrieving users");
            response.setStatusCode(400);   // bad request
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        }
    }

    @PutMapping("update-user")
    public ResponseEntity<Response> updateUser(@RequestParam("name") String name, @RequestParam("city") String city, 
                            @RequestParam("email") String email) {
        try{
            User user = usersService.updateUser(name, city, email);
            if(user != null){
                Response response = new Response();
                response.setMessage("User updated successfully.");
                response.setStatusCode(200); // status ok
                response.setData(user);
                return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
            }else{
                Response response = new Response();
                response.setMessage("User not not found.");
                response.setStatusCode(404); //not found
                response.setData(user);
                return new ResponseEntity<Response>(response, HttpStatus.valueOf(response.getStatusCode()));
            }
        }catch(IllegalArgumentException exception){
            Response response = new Response();
            response.setMessage(exception.getMessage());
            response.setStatusCode(400); // Bad request
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch(Exception exception){
            Response response = new Response();
            response.setMessage("Failed to update user");
            response.setStatusCode(500); // Internal_server_error
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<Response> deleteUser(@RequestParam("id") int id) {
        try{
            usersService.deleteUserById(id);
            Response response = new Response();
            response.setMessage("User deleted successfully.");
            response.setStatusCode(204); // No contend or deleted user
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        }catch(IllegalArgumentException exception){
            Response response = new Response();
            response.setMessage(exception.getMessage());
            response.setStatusCode(404);  // not found
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch(Exception exception){
            Response response = new Response();
            response.setMessage("Failed to delete user");
            response.setStatusCode(500);  //Internal server error
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        }
    }

}
