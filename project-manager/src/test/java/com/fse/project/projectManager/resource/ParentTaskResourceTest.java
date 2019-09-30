package com.fse.project.projectManager.resource;

import com.fse.project.projectManager.repository.ParentTaskRepo;
import com.fse.project.projectManager.repository.model.ParentTask;
import com.fse.project.projectManager.service.ParentTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ParentTaskResourceTest {

    @Mock
    ParentTask parentTask;

    @Mock
    private ParentTaskService parentTaskService;

    @InjectMocks
    ParentTaskResource parentTaskResource = new ParentTaskResource();

    @Test
    public void thatShouldReturnTaskDetailsWhenPassingParentTask(){

        String parentTask = "parent";
        ParentTask result = new ParentTask();
        result.setParentTask(parentTask);
        Mockito.when(parentTaskService.addTask(any(String.class))).thenReturn(result);
        ParentTask savedTask = parentTaskResource.addNewTask("parent");
        assertEquals(parentTask, savedTask.getParentTask());
    }

    @Test
    public void thatShouldReturnParentTaskDetails(){

        List taskList= new ArrayList<ParentTask>();

        String parentTask = "parent";
        int id = 1;
        ParentTask task = new ParentTask();
        task.setParentTask(parentTask);
        task.setParentId(id);
        taskList.add(task);

        Mockito.when(parentTaskService.allParentTask()).thenReturn(taskList);
        Iterable<ParentTask> tasks = parentTaskResource.fetchAllUsers();
        for(ParentTask resultTask : tasks){
            assertEquals("parent", resultTask.getParentTask());
        }
    }
}
