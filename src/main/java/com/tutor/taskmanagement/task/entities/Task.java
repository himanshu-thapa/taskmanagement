package com.tutor.taskmanagement.task.entities;

import com.tutor.taskmanagement.task.entities.enums.TaskStatus;
import lombok.*;

import java.util.Date;

//@Entity
//@Table(name = "todo_task")
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Data
public class Task {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")*/
    @Setter(AccessLevel.NONE)
    private Long id;
    private String taskName; //task_name
    private Date issueDate;
    private Date completionDate;
    private TaskStatus status;

    /*Constructor*//*
    public Task() {
    }

    public Task(String taskName, Date issueDate, Date completionDate, TaskStatus status) {
        this.taskName = taskName;
        this.issueDate = issueDate;
        this.completionDate = completionDate;
        this.status = status;
    }

    *//*Getter and Setters*//*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", issueDate=" + issueDate +
                ", completionDate=" + completionDate +
                ", status=" + status +
                '}';
    }*/
}
