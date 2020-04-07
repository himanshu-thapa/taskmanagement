package com.tutor.taskmanagement.task.dao;

import com.tutor.taskmanagement.task.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskDAO {

    // findAll
    // -> List of task

    List<Task> findAll();

    //findById
    //-> Unique task

    Task findById(Long id);

    Task save(Task task);
}
