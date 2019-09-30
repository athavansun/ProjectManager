package com.fse.project.projectManager.resource;

import com.fse.project.projectManager.repository.model.User;
import com.fse.project.projectManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller   
@RequestMapping(path="/user")
public class UserResource {

	@Autowired
	UserService userService;

	@PostMapping(path="/add")
	public @ResponseBody User addNewUser (@RequestBody User user) {

		return userService.addNewUser(user);
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {

		return userService.allUsers();
	}
	
	@PutMapping(path="/update")
	public @ResponseBody User updateUser(@RequestBody User user){

		return userService.updateUser(user);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable("id") Integer id){

		return userService.deleteUser(id);
	}
}

