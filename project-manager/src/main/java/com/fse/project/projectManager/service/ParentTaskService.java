package com.fse.project.projectManager.service;

import com.fse.project.projectManager.repository.ParentTaskRepo;
import com.fse.project.projectManager.repository.model.ParentTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentTaskService {

    @Autowired
    private ParentTaskRepo repo;

    public ParentTask addTask(String parentTask) {
        ParentTask task = new ParentTask();
        task.setParentTask(parentTask);
        return repo.save(task);
    }

    public Iterable<ParentTask> allParentTask() {

        return repo.findAll();
    }
}
