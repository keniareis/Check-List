package com.keniareis.CheckList.controllers;

import com.keniareis.CheckList.entities.Task;
import com.keniareis.CheckList.exceptions.TaskNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class TaskController {

    private ArrayList<Task> tasks = new ArrayList<>();

    //create
    @PostMapping
    public Task createTask(@RequestBody Task newTask){
        Long proximoId = (long) tasks.size() + 1;
        newTask.setId(proximoId);
        tasks.add(newTask);
        return newTask;
    }

    //update
    @PutMapping("/{taskid}")
    public Task editTask(@PathVariable Long Id, @RequestBody Task taskUpdated) throws TaskNotFoundException {
        var newTask = tasks.stream().map(task -> {
            if(task.getId().equals(Id))
                taskUpdated.setId(task.getId());
        });
        for (Task task : tasks){
            if (task.getId().equals(Id)){
                task.setNome(taskUpdated.getNome());
                task.setDate(taskUpdated.getDate());
                task.setStatus(taskUpdated.getStatus());
                return task;
            }
        }
        throw new TaskNotFoundException("Task com o ID " + Id + " não encontrada.");
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        tasks.removeIf(task -> task.getId().equals(id));
    }




}
