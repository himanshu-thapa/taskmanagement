package com.tutor.taskmanagement.view_controller;

import com.tutor.taskmanagement.task.entities.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    //    @RequestMapping("/")
    @GetMapping({"/", "/home"})
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView("index");
//        mv.setViewName("index");
        mv.addObject("task", new Task());
        return mv;
    }

    @PostMapping("/task/post")
    public ModelAndView createNewPost(@ModelAttribute Task task){
        System.out.println(task);
        return null;
    }


}
