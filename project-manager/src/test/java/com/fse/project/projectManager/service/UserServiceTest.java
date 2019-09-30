package com.fse.project.projectManager.service;

import com.fse.project.projectManager.repository.UserRepo;
import com.fse.project.projectManager.repository.model.User;
import com.fse.project.projectManager.service.TaskService;
import com.fse.project.projectManager.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService = new UserService();

    @Mock
    private UserRepo userRepo;

    @Test
    public void thatShouldReturnNewlyAddedResource(){

        String firstName = "Athavan";
        User user = new User();
        user.setFirstName(firstName);
        Mockito.when(userRepo.save(any(User.class))).thenReturn(user);
        User result = userService.addNewUser(user);
        assertEquals(firstName, result.getFirstName());
    }

    @Test
    public void thatShouldReturnListOfUser(){

        List userList = new ArrayList<>();
        User user = new User();
        userList.add(user);
        Mockito.when(userRepo.findAll()).thenReturn(userList);
        List result = (List) userService.allUsers();
        assertEquals(1, result.size());
    }

    @Test
    public void thatShouldReturnUpdatedUserDetails(){

        String firstName = "Athavan";
        User user = new User();
        user.setFirstName(firstName);
        Mockito.when(userRepo.save(user)).thenReturn(user);
        User result = userService.updateUser(user);
        assertEquals(firstName, result.getFirstName());
    }

    @Test
    public void thatShouldDeleteUserDetailsBasedOnId(){

        String result = userService.deleteUser(1);
        assertEquals("return", result);
    }
}
