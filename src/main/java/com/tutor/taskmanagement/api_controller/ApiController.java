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

}
