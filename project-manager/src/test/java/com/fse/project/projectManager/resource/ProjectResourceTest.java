package com.fse.project.projectManager.resource;

import com.fse.project.projectManager.model.ProjectModel;
import com.fse.project.projectManager.repository.ProjectRepo;
import com.fse.project.projectManager.repository.TaskRepo;
import com.fse.project.projectManager.repository.model.Project;
import com.fse.project.projectManager.repository.model.Task;
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
public class ProjectResourceTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    ProjectResource projectResource = new ProjectResource();

    @Test
    public void thatShouldReturnProjectWhenAddNewModel(){

        String projectName = "project1";
        ProjectModel projectModel = new ProjectModel();
        projectModel.setProjectName(projectName);
        Project project = new Project();
        project.setProjectId(1);

        Mockito.when(projectService.saveNewProject(any(ProjectModel.class))).thenReturn(projectModel);

        ProjectModel result = projectResource.addNewProject(projectModel);
        assertEquals(projectName, result.getProjectName());
    }

    @Test
    public void thatShouldReturnUpdatedProjectWhenUpdatingModel(){

        String projectName = "project1";
        ProjectModel projectModel = new ProjectModel();
        projectModel.setProjectName(projectName);

        Mockito.when(projectService.updateProject(any(ProjectModel.class))).thenReturn(projectModel);

        ProjectModel result = projectResource.updateProject(projectModel);
        assertEquals(projectName, projectModel.getProjectName());
    }

    @Test
    public void thatShouldReturnListOfProjects(){

        int projectSize = 1;
        List projectList = new ArrayList<>();
        Project project = new Project();
        projectList.add(project);
        List<Task> task = new ArrayList<>();
        task.add(new Task());

        Mockito.when(projectService.fetchAllProjects()).thenReturn(projectList);

        List<ProjectModel> result = projectResource.getAllProjects();
        assertEquals(projectSize, result.size());
    }

    @Test
    public  void thatShouldDeleteObject(){

        Mockito.when(projectService.deleteProject(any(Integer.class))).thenReturn("deleted");
        String result = projectResource.deleteProject(1);
        assertEquals("deleted", result);
    }
}
