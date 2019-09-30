package com.fse.project.projectManager.service;

import com.fse.project.projectManager.model.ProjectModel;
import com.fse.project.projectManager.repository.ProjectRepo;
import com.fse.project.projectManager.repository.TaskRepo;
import com.fse.project.projectManager.repository.UserRepo;
import com.fse.project.projectManager.repository.model.Project;
import com.fse.project.projectManager.repository.model.Task;
import com.fse.project.projectManager.repository.model.User;
import com.fse.project.projectManager.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProjectServiceTest {

    @Mock
    TaskRepo taskRepo;

    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    ProjectService projectService = new ProjectService();

    @Test
    public void thatShouldReturnProjectWhenAddNewModel(){

        String projectName = "project";
        ProjectModel projectModel = new ProjectModel();
        projectModel.setProjectName(projectName);
        Project project = new Project();
        project.setProjectId(1);

        Mockito.when(projectRepo.save(any(Project.class))).thenReturn(project);

        ProjectModel result = projectService.saveNewProject(projectModel);
        assertEquals(projectName, result.getProjectName());
    }

    @Test
    public void thatShouldReturnUpdatedProjectWhenUpdatingModel(){

        String projectName = "project";
        ProjectModel projectModel = new ProjectModel();
        projectModel.setProjectName(projectName);
        projectModel.setProjectId(1);
        Project project = new Project();
        project.setProjectId(1);

        Optional<Project> projectOptional = Optional.of(project);

        Mockito.when(projectRepo.save(any(Project.class))).thenReturn(project);
        Mockito.when(projectRepo.findById(any(Integer.class))).thenReturn(projectOptional);

        ProjectModel result = projectService.updateProject(projectModel);
        assertEquals(projectName, result.getProjectName());
    }

    @Test
    public void thatShouldReturnListOfProjects(){

        int projectSize = 1;
        List projectList = new ArrayList<>();
        Project project = new Project();
        project.setUserId(1);
        projectList.add(project);
        List<Task> task = new ArrayList<>();
        task.add(new Task());
        User userDetails = new User();
        userDetails.setFirstName("Athavan");
        Optional<User> user = Optional.of(userDetails);

        Mockito.when(projectRepo.findAll()).thenReturn(projectList);
        Mockito.when(taskRepo.findAllByProjectId(any(Integer.class))).thenReturn(task);
        Mockito.when(taskRepo.findAllByProjectIdAndStatus(any(Integer.class), any(String.class))).thenReturn(task);
        Mockito.when(userRepo.findById(any(Integer.class))).thenReturn(user);

        List<ProjectModel> result = projectService.fetchAllProjects();
        assertEquals(projectSize, result.size());
    }

    @Test
    public  void thatShouldDeleteObject(){

        String result = projectService.deleteProject(1);
        assertEquals("deleted", result);
    }
}
