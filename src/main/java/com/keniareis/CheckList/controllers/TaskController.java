package com.keniareis.CheckList.controllers;

import com.keniareis.CheckList.entities.Task;
import com.keniareis.CheckList.exceptions.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private ArrayList<Task> tasks = new ArrayList<>();

    //create
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task newTask){
        Long nextId = (long) tasks.size() + 1;
        newTask.setId(nextId);
        tasks.add(newTask);
        return ResponseEntity.ok(newTask);
    }

    //update
    @PutMapping("/{taskid}")
    public Task editTask(@PathVariable Long Id, @RequestBody Task taskUpdated) throws TaskNotFoundException {

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

    //delete
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        tasks.removeIf(task -> task.getId().equals(id));
    }

    //end task
    @PutMapping("/tasks/{taskId}/status")
    public Task endTask(@PathVariable("taskid") Long Id, @RequestBody String status) throws TaskNotFoundException {
        for (Task task : tasks) {
            if (task.getId().equals(Id)) {
                task.setStatus(status);
                return ResponseEntity.ok(task).getBody();
            }
        }
        throw new TaskNotFoundException("Task com o ID " + Id + " não encontrada.");
    }

    //search one task
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> searchTasks(@PathVariable Long id){
        Optional<Task> task =  tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //search task by date
    @GetMapping("/{taskDate}")
    public ResponseEntity<List<Task>> searchTaskByDate(LocalDate date){
        List<Task> tasksByDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDate().equals(date)) {
                tasksByDate.add(task);
            }
        }
        return ResponseEntity.ok(tasksByDate);
    }


}
