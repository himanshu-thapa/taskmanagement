package com.tutor.taskmanagement.api_controller;

import com.tutor.taskmanagement.task.entities.Task;
import com.tutor.taskmanagement.task.entities.enums.TaskStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;

@RestController
public class ApiController {

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
//    @GetMapping("/welcome")
    public String sayHello() {
        return "Hello World";
    }

    @PostConstruct
    public void display(){
        Task task = new Task();
        task.setTaskName("Task 1");
        task.setIssueDate(new Date());
        task.setCompletionDate(new Date());
        task.setStatus(TaskStatus.INPROGRESS);

        System.out.println("TASK:"+task);
    }
}
