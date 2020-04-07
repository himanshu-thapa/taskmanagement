package com.tutor.taskmanagement.task;

import com.tutor.taskmanagement.task.dto.TaskDTO;
import com.tutor.taskmanagement.task.entities.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class Mapper {
    @Autowired
    private ModelMapper modelMapper;

    public TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        taskDTO.setIssueDateConverted(task.getIssueDate());
        taskDTO.setCompletionDateConverted(task.getCompletionDate());
        return taskDTO;
    }

    public Task convertToEntity(TaskDTO taskDTO) throws ParseException {
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setIssueDate(taskDTO.getIssueDateConverted());
        task.setCompletionDate(taskDTO.getCompletionDateConverted());
        return task;
    }

}
