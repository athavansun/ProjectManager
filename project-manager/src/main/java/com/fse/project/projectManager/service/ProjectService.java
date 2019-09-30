package com.fse.project.projectManager.service;

import com.fse.project.projectManager.model.ProjectModel;
import com.fse.project.projectManager.repository.ProjectRepo;
import com.fse.project.projectManager.repository.TaskRepo;
import com.fse.project.projectManager.repository.UserRepo;
import com.fse.project.projectManager.repository.model.Project;
import com.fse.project.projectManager.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    TaskRepo taskRepo;

    public ProjectModel saveNewProject(ProjectModel projectModel) {

        Project project = new Project();
        project.setProject(projectModel.getProjectName());
        project.setStartDate(projectModel.getStartDate());
        project.setEndDate(projectModel.getEndDate());
        project.setPriority(projectModel.getPriority());
        project.setUserId(projectModel.getUserId());

        projectModel.setProjectId(projectRepo.save(project).getProjectId());
        projectModel.setCompletedTaskNumber(0);
        projectModel.setTaskNumber(0);

        return projectModel;
    }

    public ProjectModel updateProject(ProjectModel projectModel) {

        Optional<Project> projectOptional = projectRepo.findById(projectModel.getProjectId());
        if(projectOptional.isPresent()){
            Project project = projectOptional.get();
            project.setProject(projectModel.getProjectName());
            project.setStartDate(projectModel.getStartDate());
            project.setEndDate(projectModel.getEndDate());
            project.setPriority(projectModel.getPriority());
            project.setUserId(projectModel.getUserId());
            projectRepo.save(project);
            return projectModel;
        }else{
            return null;
        }
    }

    public List<ProjectModel> fetchAllProjects() {

        Iterable<Project> projectList =  projectRepo.findAll();
        List<ProjectModel> projectResponseList = new ArrayList<>();
        for(Project project: projectList){
            ProjectModel projectModel = new ProjectModel();
            projectModel.setProjectId(project.getProjectId());
            projectModel.setProjectName(project.getProject());
            projectModel.setStartDate(project.getStartDate());
            projectModel.setEndDate(project.getEndDate());
            projectModel.setPriority(project.getPriority());
            projectModel.setTaskNumber(taskRepo.findAllByProjectId(project.getProjectId()).size());
            projectModel.setCompletedTaskNumber(taskRepo.findAllByProjectIdAndStatus(project.getProjectId(), "COMPLETED").size());
            projectModel.setUserId(project.getUserId());
            if(project.getUserId() != null){
                Optional<User> userOptional = userRepo.findById(project.getUserId());
                if( userOptional != null){
                    projectModel.setManager(userOptional.get().getFirstName());
                }
            }
            projectResponseList.add(projectModel);
        }
        return projectResponseList;
    }

    public String deleteProject(int id) {

        projectRepo.deleteById(id);
        return "deleted";
    }
}
