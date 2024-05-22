package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.Response.Response;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService userServeTest;

    @InjectMocks
    UserController userControlerTest;

    @Test
    public void TestSaveUser() {
        User expUser = new User(1, "Luffy", "Foosha Village", "luffy@gmail.com");
        when(userServeTest.saveNewUser(expUser)).thenReturn(expUser);

        ResponseEntity<Response> actualResponse = userControlerTest.saveNewUser(expUser);

        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals("User saved successfully", actualResponse.getBody().getMessage());
        assertEquals(expUser, actualResponse.getBody().getData());
    }

    @Test
    public void testGetUserById() {
        User expUser = new User(101, "Zoro", "Shimotsuki Village", "zoro@gmail.com");
        when(userServeTest.getUserById(101)).thenReturn(expUser);

        ResponseEntity<Response> actualResponse = userControlerTest.getUserById(101);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("User retrieved successfully.", actualResponse.getBody().getMessage());
        assertEquals(expUser, actualResponse.getBody().getData());
    }

    @Test
    public void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(102, "Nami", "Cocoyasi Village", "nami@gmail.com"));
        userList.add(new User(101, "Usopp", "Syrup Village", "ussop@gmail.com"));
        userList.add(new User(103, "Nico Robin", "Ohara", "nicoRobin@gmail.com"));

        when(userServeTest.getAllUser()).thenReturn(userList);

        ResponseEntity<Response> actualResponse = userControlerTest.getAllUser();

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("All users data retrieved successfully", actualResponse.getBody().getMessage());
        assertEquals(userList, actualResponse.getBody().getData());
    }

    @Test
    public void testUpdateUser_Success() {
        String name = "Francky";
        String city = "South Blue";
        String email = "francky@example.com";
        User expUser = new User(101, name, city, email);
        when(userServeTest.updateUser(name, city, email)).thenReturn(expUser);

        ResponseEntity<Response> actualResponse = userControlerTest.updateUser(name, city, email);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("User updated successfully.", actualResponse.getBody().getMessage());
        assertEquals(expUser, actualResponse.getBody().getData());
    }

    @Test
    public void testDeleteUser_Success() {
        int userIdToDelete = 101;
        doNothing().when(userServeTest).deleteUserById(userIdToDelete);

        ResponseEntity<Response> actualResponse = userControlerTest.deleteUser(userIdToDelete);

        assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());
        assertEquals("User deleted successfully.", actualResponse.getBody().getMessage());
    }

}
