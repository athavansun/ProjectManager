package com.fse.project.projectManager.service;

import com.fse.project.projectManager.model.TaskModel;
import com.fse.project.projectManager.repository.ParentTaskRepo;
import com.fse.project.projectManager.repository.ProjectRepo;
import com.fse.project.projectManager.repository.TaskRepo;
import com.fse.project.projectManager.repository.UserRepo;
import com.fse.project.projectManager.repository.model.ParentTask;
import com.fse.project.projectManager.repository.model.Project;
import com.fse.project.projectManager.repository.model.Task;
import com.fse.project.projectManager.repository.model.User;
import com.fse.project.projectManager.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TaskServiceTest {

    @InjectMocks
    TaskService taskService = new TaskService();

    @Mock
    TaskRepo taskRepo;

    @Mock
    private ParentTaskRepo repo;

    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private UserRepo userRepo;

    @Test
    public void thatShouldSaveTheTask(){

        TaskModel taskModel = new TaskModel();
        String result = taskService.addNewTask(taskModel);
        assertEquals("Saved", result);
    }

    @Test
    public void thatShouldSaveTheTaskWhenPassingParentTask(){

        TaskModel taskModel = new TaskModel();
        taskModel.setParentTask(true);
        String result = taskService.addNewTask(taskModel);
        assertEquals("Saved", result);
    }

    @Test
    public void thatShouldReturnAllTasks(){

        List<Task> taskObjList = new ArrayList<>();
        Task task = new Task();
        Integer parentId = 1;
        Integer projectId = 1;
        Integer userId = 1;
        task.setParentId(parentId);
        task.setProjectId(projectId);
        task.setUserId(userId);
        taskObjList.add(task);
        Project project = new Project();
        project.setProject("My Project");
        User user = new User();
        user.setFirstName("Athavan");

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("My Parent Task");
        Optional<ParentTask> pTaskOptional = Optional.of(parentTask);
        Optional<Project> pOptional = Optional.of(project);
        Optional<User> userOptional = Optional.of(user);

        Mockito.when(userRepo.findById(any(Integer.class))).thenReturn(userOptional);
        Mockito.when(projectRepo.findById(any(Integer.class))).thenReturn(pOptional);
        Mockito.when(repo.findById(any(Integer.class))).thenReturn(pTaskOptional);
        Mockito.when(taskRepo.findAll()).thenReturn(taskObjList);
        List result = new ArrayList();
        result = taskService.getAllTasks();
        assertEquals(1,result.size());
    }

    @Test
    public void thatShouldReturnNullWhenEntryNotAvailable(){

        TaskModel taskModel = new TaskModel();
        Object result = taskService.updateTask(taskModel);
        assertNull(result);
    }

    @Test
    public void thatShouldReturnUpdatedTaskWhenEntryAvailable(){

        String myTask = "MyTask";
        Task task = new Task();
        task.setTask(myTask);
        Optional<Task> taskList = Optional.of(task);
        Mockito.when(taskRepo.findById(any(Integer.class))).thenReturn(taskList);
        Mockito.when(taskRepo.save(any(Task.class))).thenReturn(task);
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskId(1);
        taskModel.setTaskName(myTask);
        Task result = taskService.updateTask(taskModel);
        assertEquals(myTask,result.getTask());
    }

    @Test
    public void thatShouldDeleteTaskById(){

        String result = taskService.deleteTask(1);
        assertEquals("return", result);
    }

    @Test
    public void thatShouldReturnListOfTaskByProjectId(){

        List<Task> taskObjList = new ArrayList<>();
        Task task = new Task();
        Integer parentId = 1;
        Integer projectId = 1;
        Integer userId = 1;
        task.setParentId(parentId);
        task.setProjectId(projectId);
        task.setUserId(userId);
        taskObjList.add(task);
        Project project = new Project();
        project.setProject("My Project");
        User user = new User();
        user.setFirstName("Athavan");

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("My Parent Task");
        Optional<ParentTask> pTaskOptional = Optional.of(parentTask);
        Optional<Project> pOptional = Optional.of(project);
        Optional<User> userOptional = Optional.of(user);

        Mockito.when(userRepo.findById(any(Integer.class))).thenReturn(userOptional);
        Mockito.when(projectRepo.findById(any(Integer.class))).thenReturn(pOptional);
        Mockito.when(repo.findById(any(Integer.class))).thenReturn(pTaskOptional);
        Mockito.when(taskRepo.findAllByProjectId(any(Integer.class))).thenReturn(taskObjList);
        List result = (List) taskService.getTaskByProjecId(1);
        assertEquals(1,result.size());
    }
}
