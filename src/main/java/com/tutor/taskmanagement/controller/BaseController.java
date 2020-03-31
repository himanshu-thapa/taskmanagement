package com.tutor.taskmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller
public class BaseController {

//    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    @GetMapping("/welcome")
//    @ResponseBody
    public String sayHello() {
        return "Hello World";
    }
}
