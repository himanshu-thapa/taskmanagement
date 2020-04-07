package com.tutor.taskmanagement.task.dao.daoImpl;

import com.tutor.taskmanagement.task.dao.TaskDAO;
import com.tutor.taskmanagement.task.entities.Task;
import com.tutor.taskmanagement.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDAOImpl implements TaskDAO {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
//        Task task = taskRepository.findById(id).orElseThrow(ClassNotFoundException::new);
        Task task = taskRepository.getOneById(id);
        return task;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }
}
