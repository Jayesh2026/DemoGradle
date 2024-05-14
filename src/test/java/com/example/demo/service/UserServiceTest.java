package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.ArrayList;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepositoryTest;
    
    @InjectMocks
    UserService userServiceTest;
    

    @Test
    public void testSaveUser(){
        User expUser = new User(101, "Dummy", "Dumble");
        when(userRepositoryTest.saveNewUser(expUser)).thenReturn(expUser);
        User actUser = userServiceTest.saveNewUser(expUser);
        assertEquals(expUser, actUser);
    }

    @Test
    public void testGetUserById(){
        User expUser = new User(102, "Dummy2", "Dumbalkar");
        when(userRepositoryTest.getUserById(102)).thenReturn(expUser);
        User actUser = userServiceTest.getUserById(102);
        assertEquals(expUser, actUser);
    }


    @Test
    public void testGetAllUser(){
        List<User> expUserList = new ArrayList<User>();
        expUserList.add(new User(1, "Jayesh", "Pune"));
        expUserList.add(new User(2, "Supriya", "Mumbai"));
        expUserList.add(new User(3, "Naushad", "Delhi"));

        when(userRepositoryTest.getAllUsers()).thenReturn(expUserList);
        List<User> actuList = userServiceTest.getAllUser();

        assertEquals(expUserList, actuList);
    }

    @Test
    public void testUpdatedUser(){
        User expUser = new User(102, "Dummy3", "America");
        when(userRepositoryTest.updateUser(102, "Dummy3", "America")).thenReturn(expUser);
        User updUser = userServiceTest.updateUser(102, "Dummy3", "America");
        assertEquals(expUser, updUser);
    }

    @Test
    public void testDeleteUser(){
        int deleteId = 105;
        doNothing().when(userRepositoryTest).deleted(deleteId);
        userServiceTest.deleteUserById(deleteId);
        verify(userRepositoryTest).deleted(deleteId);
    }
    
}
