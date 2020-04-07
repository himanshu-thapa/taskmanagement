package com.tutor.taskmanagement.task.entities;

import com.tutor.taskmanagement.task.entities.enums.TaskStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    private String taskName; //task_name
    private Date issueDate;
    private Date completionDate;
    private TaskStatus status;

}
