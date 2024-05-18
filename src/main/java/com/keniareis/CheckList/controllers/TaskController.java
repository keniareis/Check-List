package com.keniareis.CheckList.controllers;

import com.keniareis.CheckList.entities.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class TaskController {

    private ArrayList<Task> task = new ArrayList<>();

    //create
    @PostMapping
    public Task createTask(@RequestBody Task newTask){
        Long proximoId = (long) task.size() + 1;
        newTask.setId(proximoId);
        task.add(newTask);
        return newTask;
    }

    //update
    @PutMapping("/{taskid}")
    public Optional<Task> editTask(@PathVariable UUID Id, @RequestBody Task taskUpdated) throws {

    }




}
