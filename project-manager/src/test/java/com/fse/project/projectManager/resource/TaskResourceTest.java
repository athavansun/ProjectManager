package com.fse.project.projectManager.resource;

import com.fse.project.projectManager.model.TaskModel;
import com.fse.project.projectManager.repository.ParentTaskRepo;
import com.fse.project.projectManager.repository.ProjectRepo;
import com.fse.project.projectManager.repository.TaskRepo;
import com.fse.project.projectManager.repository.UserRepo;
import com.fse.project.projectManager.repository.model.Task;
import com.fse.project.projectManager.service.TaskService;
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
public class TaskResourceTest {

    @InjectMocks
    TaskResource taskResource = new TaskResource();

    @Mock
    TaskService taskService;

    @Test
    public void thatShouldSaveTheTask(){

        TaskModel taskModel = new TaskModel();
        Mockito.when(taskService.addNewTask(any(TaskModel.class))).thenReturn("Saved");
        String result = taskResource.addNewTask(taskModel);
        assertEquals("Saved", result);
    }

    @Test
    public void thatShouldSaveTheTaskWhenPassingParentTask(){

        TaskModel taskModel = new TaskModel();
        taskModel.setParentTask(true);
        Mockito.when(taskService.addNewTask(any(TaskModel.class))).thenReturn("Saved");
        String result = taskResource.addNewTask(taskModel);
        assertEquals("Saved", result);
    }

    @Test
    public void thatShouldReturnAllTasks(){

        List<TaskModel> taskList = new ArrayList<>();
        TaskModel task = new TaskModel();
        taskList.add(task);
        Mockito.when(taskService.getAllTasks()).thenReturn(taskList);
        List result = taskResource.getAllTasks();
        assertEquals(1,result.size());
    }

    @Test
    public void thatShouldReturnUpdatedTask(){

        TaskModel taskModel = new TaskModel();
        Task task = new Task();
        String taskName = "My Task";
        task.setTask(taskName);
        Mockito.when(taskService.updateTask(any(TaskModel.class))).thenReturn(task);

        Task result = taskResource.updateTask(taskModel);
        assertEquals(taskName ,result.getTask());
    }

    @Test
    public void thatShouldDeleteTaskById(){

        Mockito.when(taskService.deleteTask(any(Integer.class))).thenReturn("return");
        String result = taskResource.deleteTask(1);
        assertEquals("return", result);
    }

    @Test
    public void thatShouldReturnListOfTaskByProjectId(){

        List<TaskModel> taskObjList = new ArrayList<>();
        TaskModel task = new TaskModel();
        taskObjList.add(task);
        Mockito.when(taskService.getTaskByProjecId(any(Integer.class))).thenReturn(taskObjList);
        List result = (List) taskResource.getTasksByProjectId(1);
        assertEquals(1,result.size());
    }
}
