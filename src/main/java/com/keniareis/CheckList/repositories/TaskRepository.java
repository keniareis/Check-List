package com.keniareis.CheckList.repositories;

import com.keniareis.CheckList.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByName(String name);
}
