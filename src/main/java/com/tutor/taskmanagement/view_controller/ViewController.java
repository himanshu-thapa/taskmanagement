package com.tutor.taskmanagement.view_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

//    @RequestMapping("/")
    @GetMapping({"/","/home"})
    public ModelAndView getIndex(){
        ModelAndView mv = new ModelAndView("index");
//        mv.setViewName("index");
        return mv;
    }
}
