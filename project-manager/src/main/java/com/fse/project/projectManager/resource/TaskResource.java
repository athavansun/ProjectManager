package com.fse.project.projectManager.resource;

import com.fse.project.projectManager.model.TaskModel;
import com.fse.project.projectManager.repository.model.Task;
import com.fse.project.projectManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(path="/task")
public class TaskResource {

	@Autowired
	TaskService taskService;

	@PostMapping(path="/add")
	public @ResponseBody String addNewTask (@RequestBody TaskModel task) {

		return taskService.addNewTask(task);
	}

	@GetMapping(path="/all")
	public @ResponseBody List<TaskModel> getAllTasks() {

		return taskService.getAllTasks();
	}
	
	@PutMapping(path="/update")
	public @ResponseBody Task updateTask(@RequestBody TaskModel task){

		return taskService.updateTask(task);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTask(@PathVariable("id") Integer id){

		return taskService.deleteTask(id);
		
	}
	
	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public @ResponseBody Iterable<TaskModel> getTasksByProjectId(@PathVariable("id") Integer id){

		return taskService.getTaskByProjecId(id);
	}

}
