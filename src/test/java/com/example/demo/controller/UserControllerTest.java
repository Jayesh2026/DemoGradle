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

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    
    @Mock
    UserService userServeTest;

    @InjectMocks
    UserController userContrTest;

    @Test
    public void TestSaveUser(){
        User expUser = new User(1, "Luffy", "Foosha Village");
        when(userServeTest.saveNewUser(expUser)).thenReturn(expUser);
        User acutalUser = userContrTest.saveNewUser(expUser);
        assertEquals(expUser, acutalUser);
    }

    @Test
    public void testGetUserById(){
        User expUSer = new User(101, "Zoro", "Shimotsuki Village");
        when(userServeTest.getUserById(101)).thenReturn(expUSer);
        User actUser = userContrTest.getUserById(101);
        assertEquals(expUSer, actUser);
    }

    @Test
    public void testGetAllUser(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(102, "Nami", "Cocoyasi Village"));
        userList.add(new User(101, "Usopp", "Syrup Village"));
        userList.add(new User(103, "Nico Robin", "Ohara"));

        when(userServeTest.getAllUser()).thenReturn(userList);
        List<User> acList = userContrTest.getAllUser();
        assertEquals(userList, acList);
    }

    @Test
    public void testUpdateUser(){
        User expUser = new User(101, "Francky", "South Blue");
        when(userServeTest.updateUser(101,"Francky", "South Blue")).thenReturn(expUser);
        User acUser = userContrTest.updateUser(101, "Francky", "South Blue");
        assertEquals(expUser, acUser);
    }

    @Test
    public void testDeleteUSer(){
        int userIdToDelete = 101;
        doNothing().when(userServeTest).deleteUserById(userIdToDelete);
        userContrTest.deleteUser(userIdToDelete);
        verify(userServeTest).deleteUserById(userIdToDelete);
    }

}
