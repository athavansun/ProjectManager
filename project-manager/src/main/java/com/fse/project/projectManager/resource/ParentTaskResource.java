package com.fse.project.projectManager.resource;

import com.fse.project.projectManager.repository.model.ParentTask;
import com.fse.project.projectManager.service.ParentTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(path="/parenttask")
public class ParentTaskResource {

    @Autowired
    private ParentTaskService parentTaskService;

	@PostMapping(path="/add")
	public @ResponseBody
	ParentTask addNewTask (@RequestBody String task) {

        return parentTaskService.addTask(task);
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<ParentTask> fetchAllUsers() {

	    return parentTaskService.allParentTask();
	}


}
