package com.tutor.taskmanagement.task.repository;

import com.tutor.taskmanagement.task.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    
}
