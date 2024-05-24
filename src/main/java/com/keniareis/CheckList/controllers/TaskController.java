package com.keniareis.CheckList.controllers;

import com.keniareis.CheckList.entities.Task;
import com.keniareis.CheckList.exceptions.TaskNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private ArrayList<Task> tasks = new ArrayList<>();

    //create
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task newTask){
        if(tasks.isEmpty()) {
            Long nextId = (long) tasks.size() + 1; //deixar id fora ja q nao é mais utilizavel, nao repetir existente
            newTask.setId(nextId);
            tasks.add(newTask);
        }else {
            Task nextId = tasks.get(tasks.size() -1);
            newTask.setId(nextId.getId() + 1);
            tasks.add(newTask);
        }
        return ResponseEntity.ok(newTask);
    }

    //update
    @PutMapping("/{id}")
    public Task editTask(@PathVariable("id") Long id, @RequestBody Task taskUpdated) throws TaskNotFoundException {

        for (Task task : tasks){
            if (task.getId().equals(id)){
                task.setNome(taskUpdated.getNome());
                task.setDate(taskUpdated.getDate());
                task.setStatus(taskUpdated.getStatus());
                return task;
            }
        }
        throw new TaskNotFoundException("Task com o ID " + id + " não encontrada.");
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        tasks.removeIf(task -> task.getId().equals(id));
    }

    //end task
    @PutMapping("/{id}/status")
    public Task endTask(@PathVariable("id") Long id, @RequestBody String status) throws TaskNotFoundException {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setStatus(status);
                return ResponseEntity.ok(task).getBody();
            }
        }
        throw new TaskNotFoundException("Task com o ID " + id + " não encontrada.");
    }

    //search one task
    @GetMapping("/search/{id}")
    public ResponseEntity<Task> searchTasks(@PathVariable("id") Long id){
        Optional<Task> task =  tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //search task by date
    @GetMapping("/searchByDate/{date}")
    public ResponseEntity<List<Task>> searchTaskByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<Task> tasksByDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDate().equals(date)) {
                tasksByDate.add(task);
            }
        }
        return ResponseEntity.ok(tasksByDate);
    }


}
