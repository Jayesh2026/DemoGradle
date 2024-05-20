package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser(){
        User expUser = new User(101, "Dummy", "Dumble", "dummy@gmail.com");
        when(userRepositoryTest.saveNewUser(expUser)).thenReturn(expUser);
        User actUser = userServiceTest.saveNewUser(expUser);
        assertEquals(expUser, actUser);
    }

    @Test
    public void testGetUserById(){
        User expUser = new User(102, "Dummy2", "Dumbalkar", "dummy2@gmail.com");
        when(userRepositoryTest.findUserById(102)).thenReturn(expUser);
        User actUser = userServiceTest.getUserById(102);
        assertEquals(expUser, actUser);
    }


    @Test
    public void testGetAllUser(){
        List<User> expUserList = new ArrayList<User>();
        expUserList.add(new User(1, "Jayesh", "Pune", "jayesh@gmail.com"));
        expUserList.add(new User(2, "Supriya", "Mumbai", "supriya@gmail.com"));
        expUserList.add(new User(3, "Naushad", "Delhi", "naushad@gmail.com"));

        when(userRepositoryTest.getAllUsers()).thenReturn(expUserList);
        List<User> actuList = userServiceTest.getAllUser();

        assertEquals(expUserList, actuList);
    }

    @Test
    public void testUpdatedUser(){
        User existingUser = new User();
        existingUser.setId(101);
        existingUser.setName("dummy");
        existingUser.setCity("OldCity");
        existingUser.setEmail("dummy3@gmail.com");
        
        User expUser = new User();
        expUser.setId(101);
        expUser.setName("dummy3");
        expUser.setCity("America");
        expUser.setEmail("dummy3@gmail.com");

        // Stub the repository methods
        when(userRepositoryTest.findUserByEmail("dummy3@gmail.com")).thenReturn(existingUser);
        when(userRepositoryTest.updateUserByEmail("Dummy3", "America", "dummy3@gmail.com")).thenReturn(expUser);
        User updUser = userServiceTest.updateUser("Dummy3", "America", "dummy3@gmail.com");
        assertEquals(expUser, updUser);
    }

    @Test
    public void testDeleteUser() {
        int deleteId = 105;

        when(userRepositoryTest.findUserById(deleteId)).thenReturn(new User());

        userServiceTest.deleteUserById(deleteId);

        verify(userRepositoryTest).findUserById(deleteId);
        verify(userRepositoryTest).deleteById(deleteId);
    }
}
