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
        User expUser = new User(1, "Luffy", "Foosha Village", "luffy@gmail.com");
        when(userRepositoryTest.saveNewUser(expUser)).thenReturn(expUser);
        User actUser = userRepositoryTest.saveNewUser(expUser);
        assertEquals(expUser, actUser);
    }

    @Test
    public void testFindUserById(){
        User expUser = new User(1, "Francky", "South Blue", "francky@gmail.com");
        when( userRepositoryTest.findUserById(1)).thenReturn(expUser);
        User actualUser = userRepositoryTest.findUserById(1);
        assertEquals(expUser, actualUser);
    }

    @Test
    public void testGetAllUser(){
        List<User> expUserList = new ArrayList<User>();
        expUserList.add(new User(101, "Nami", "Cocoyasi Village", "nami@gmail.com"));
        expUserList.add(new User(102, "Brook", "South Blue", "brook@gmail.com"));
        expUserList.add(new User(103, "Usoop", "Syrup Village", "usoop@gmail.com"));

        when(userRepositoryTest.getAllUsers()).thenReturn(expUserList);
        List<User> actuList = userRepositoryTest.getAllUsers();
        assertEquals(expUserList, actuList);
    }

    @Test
    public void testDeleteUserById(){
        int id = 101;
        doNothing().when(userRepositoryTest).deleteById(id);
        userRepositoryTest.deleteById(id);
        verify(userRepositoryTest).deleteById(id);
    }

    @Test
    public void testFindUserByEmail(){
        User expUser = new User(1, "Francky", "South Blue", "francky@gmail.com");
        when( userRepositoryTest.findUserByEmail("francky@gmail.com")).thenReturn(expUser);
        User actualUser = userRepositoryTest.findUserByEmail("francky@gmail.com");
        assertEquals(expUser, actualUser);
    }

    @Test
    public void testUpdatedUser(){
        User existingUser = new User();
        existingUser.setId(101);
        existingUser.setName("dummy");
        existingUser.setCity("Dummy City");
        existingUser.setEmail("dummy3@gmail.com");
        
        User expUser = new User();
        expUser.setId(101);
        expUser.setName("dummy3");
        expUser.setCity("America");
        expUser.setEmail("dummy3@gmail.com");

        when(userRepositoryTest.updateUserByEmail("Dummy3", "America", "dummy3@gmail.com")).thenReturn(expUser);
        User updUser = userRepositoryTest.updateUserByEmail("Dummy3", "America", "dummy3@gmail.com");
        assertEquals(expUser, updUser);
    }

}
