package com.tutor.taskmanagement.api_controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
//    @GetMapping("/welcome")
    public String sayHello() {
        return "Hello World";
    }
}
