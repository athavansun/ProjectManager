package com.fse.project.projectManager.service;

import com.fse.project.projectManager.repository.UserRepo;
import com.fse.project.projectManager.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addNewUser(User user) {

        User n = new User();
        n.setFirstName(user.getFirstName());
        n.setLastName(user.getLastName());
        n.setEmployeeId(user.getEmployeeId());
        return userRepo.save(n);
    }

    public Iterable<User> allUsers() {

        return userRepo.findAll();
    }

    public User updateUser(User user) {

        Optional<User> userOptional = userRepo.findById(user.getUserId());
        if(userOptional.isPresent()) {
            User u = userOptional.get();
            u.setEmployeeId(user.getEmployeeId());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            return userRepo.save(u);
        }
        return user;
    }

    public String deleteUser(Integer id) {

        userRepo.deleteById(id);
        return "return";
    }
}
