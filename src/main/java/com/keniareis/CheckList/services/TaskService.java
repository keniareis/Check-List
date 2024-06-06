package com.keniareis.CheckList.services;

import com.keniareis.CheckList.entities.Task;
import com.keniareis.CheckList.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(Sort sort) {
        return taskRepository.findAll(sort);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}