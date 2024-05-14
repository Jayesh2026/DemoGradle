package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.ArrayList;

import com.example.demo.model.User;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    UserRepository userRepositoryTest;

    @Test
    public void testSaveUser(){
        User expUser = new User(1, "Luffy", "Foosha Village");
        when(userRepositoryTest.saveNewUser(expUser)).thenReturn(expUser);
        User actUser = userRepositoryTest.saveNewUser(expUser);
        assertEquals(expUser, actUser);
    }

    @Test
    public void testGetUserById(){
        User expUser = new User(1, "Francky", "South Blue");
        when( userRepositoryTest.getUserById(1)).thenReturn(expUser);
        User actualUser = userRepositoryTest.getUserById(1);
        assertEquals(expUser, actualUser);
    }

    @Test
    public void testGetAllUser(){
        List<User> expUserList = new ArrayList<User>();
        expUserList.add(new User(101, "Nami", "Cocoyasi Village"));
        expUserList.add(new User(102, "Brook", "South Blue"));
        expUserList.add(new User(103, "Usoop", "Syrup Village"));

        when(userRepositoryTest.getAllUsers()).thenReturn(expUserList);
        List<User> actuList = userRepositoryTest.getAllUsers();
        assertEquals(expUserList, actuList);
    }

    @Test
    public void testDeleteUserById(){
        int id = 101;
        doNothing().when(userRepositoryTest).deleted(id);
        userRepositoryTest.deleted(id);
        verify(userRepositoryTest).deleted(id);
    }
}
