package com.tutor.taskmanagement.task.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TaskDTO {
    @NotNull
    @Size(min = 5, max = 100)
    private String taskName;
    @NotEmpty
    private String issueDate;
    @NotEmpty
    private String completionDate;
}
