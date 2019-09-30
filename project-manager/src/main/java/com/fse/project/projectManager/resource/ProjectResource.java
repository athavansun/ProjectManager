package com.fse.project.projectManager.resource;

import com.fse.project.projectManager.model.ProjectModel;
import com.fse.project.projectManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/project")
public class ProjectResource {

	@Autowired
	ProjectService projectService;

	@PostMapping()
	public @ResponseBody ProjectModel addNewProject(@RequestBody ProjectModel projectModel){

		return projectService.saveNewProject(projectModel);
	}
	
	@PutMapping
	public @ResponseBody ProjectModel updateProject(@RequestBody ProjectModel projectModel){

		return projectService.updateProject(projectModel);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody List<ProjectModel> getAllProjects() {

		return projectService.fetchAllProjects();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteProject(@PathVariable("id") Integer id){

		return projectService.deleteProject(id);
	}
	
}
