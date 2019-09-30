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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    private ParentTaskRepo repo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    public String addNewTask(TaskModel taskModel) {

        if(taskModel.isParentTask()){
            ParentTask pTask = new ParentTask();
            pTask.setParentTask(taskModel.getTaskName());
            repo.save(pTask);
        }else{
            Task task = new Task();
            task.setParentId(taskModel.getParentTaskId());
            task.setProjectId(taskModel.getProjectId());
            task.setTask(taskModel.getTaskName());
            task.setStartDate(taskModel.getStartDate());
            task.setEndDate(taskModel.getEndDate());
            task.setPriority(taskModel.getPriority());
            task.setUserId(taskModel.getUserId());
            task.setStatus("STARTED");
            taskRepo.save(task);
        }
        return "Saved";
    }

    public List<TaskModel> getAllTasks() {

        List<TaskModel> taskObjList = new ArrayList<>();
        List<Task> taskList =  (List<Task>) taskRepo.findAll();
        for(Task task: taskList){
            TaskModel obj = new TaskModel();
            obj.setTaskId(task.getTaskId());
            obj.setParentTaskId(task.getParentId());
            obj.setProjectId(task.getProjectId());
            obj.setTaskName(task.getTask());
            obj.setStartDate(task.getStartDate());
            obj.setEndDate(task.getEndDate());
            obj.setPriority(task.getPriority());
            obj.setStatus(task.getStatus());
            obj.setUserId(task.getUserId());
            if(task.getParentId() != null){
                Optional<ParentTask> pTaskOptional = repo.findById(task.getParentId());
                if(pTaskOptional.isPresent()){
                    obj.setParentTaskName(pTaskOptional.get().getParentTask());
                }
            }
            if(task.getProjectId() != null){
                Optional<Project> projectOptional = projectRepo.findById(task.getProjectId());
                if(projectOptional.isPresent()){
                    obj.setProjectName(projectOptional.get().getProject());
                }
            }
            if(task.getUserId() != null){
                Optional<User> userOptional = userRepo.findById(task.getUserId());
                if(userOptional.isPresent()){
                    obj.setUserName(userOptional.get().getFirstName());
                }
            }
            taskObjList.add(obj);
        }
        return taskObjList;
    }

    public Task updateTask(TaskModel taskModel) {

        Optional<Task> taskOptional = taskRepo.findById(taskModel.getTaskId());
        if(taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setParentId(taskModel.getParentTaskId());
            task.setProjectId(taskModel.getProjectId());
            task.setTask(taskModel.getTaskName());
            task.setStartDate(taskModel.getStartDate());
            task.setEndDate(taskModel.getEndDate());
            task.setPriority(taskModel.getPriority());
            task.setUserId(taskModel.getUserId());
            task.setStatus(taskModel.getStatus());
            return taskRepo.save(task);
        }
        return null;
    }

    public String deleteTask(Integer id) {

        taskRepo.deleteById(id);
        return "return";
    }

    public Iterable<TaskModel> getTaskByProjecId(Integer id) {

        List<TaskModel> taskObjList = new ArrayList<>();
        List<Task> taskList = taskRepo.findAllByProjectId(id);
        for(Task task: taskList){
            TaskModel obj = new TaskModel();
            obj.setTaskId(task.getTaskId());
            obj.setParentTaskId(task.getParentId());
            obj.setProjectId(task.getProjectId());
            obj.setTaskName(task.getTask());
            obj.setStartDate(task.getStartDate());
            obj.setEndDate(task.getEndDate());
            obj.setPriority(task.getPriority());
            obj.setStatus(task.getStatus());
            obj.setUserId(task.getUserId());
            if(task.getParentId() != null){
                Optional<ParentTask> pTaskOptional = repo.findById(task.getParentId());
                if(pTaskOptional.isPresent()){
                    obj.setParentTaskName(pTaskOptional.get().getParentTask());
                }
            }
            if(task.getProjectId() != null){
                Optional<Project> pOptional = projectRepo.findById(task.getProjectId());
                if(pOptional.isPresent()){
                    obj.setProjectName(pOptional.get().getProject());
                }
            }
            if(task.getUserId() != null){
                Optional<User> userOptional = userRepo.findById(task.getUserId());
                if(userOptional.isPresent()){
                    obj.setUserName(userOptional.get().getFirstName());
                }
            }
            taskObjList.add(obj);
        }
        return taskObjList;
    }
}
