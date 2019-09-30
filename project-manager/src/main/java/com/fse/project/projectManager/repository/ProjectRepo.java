package com.fse.project.projectManager.repository;

import com.fse.project.projectManager.repository.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepo extends CrudRepository<Project,Integer>{

}
