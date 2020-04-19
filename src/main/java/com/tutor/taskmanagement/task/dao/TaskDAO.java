package com.tutor.taskmanagement.task.dao;

import com.tutor.taskmanagement.task.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TaskDAO {

    // findAll
    // -> List of task

    Page<Task> findAll(Pageable pageable, String s);

    Page<Task> findAllByUserId(Pageable pageable, Long id,String s);

    //findById
    //-> Unique task

    Task findById(Long id);

    Task save(Task task);

    //deleteTask
    //-> void

    void deleteTask(Long id);
}
