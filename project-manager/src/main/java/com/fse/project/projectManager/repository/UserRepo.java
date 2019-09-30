package com.fse.project.projectManager.repository;


import com.fse.project.projectManager.repository.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

}