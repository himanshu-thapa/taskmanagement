package com.tutor.taskmanagement.view_controller;

import com.tutor.taskmanagement.task.entities.Task;
import com.tutor.taskmanagement.task.entities.enums.TaskStatus;
import com.tutor.taskmanagement.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ViewController {
    @Autowired
    private TaskRepository taskRepo;

    //    @RequestMapping("/")
    @GetMapping({"/", "/home"})
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView("index");
//        mv.setViewName("index");
//        mv.addObject("task", new Task());
        return mv;
    }

    @GetMapping("/task/add")
    public ModelAndView showAddTaskForm() {
        ModelAndView mv = new ModelAndView("add-task-form");
        mv.addObject("task", new Task());
        return mv;
    }

    @PostMapping("/task/post")
    @ResponseBody
    public String createNewPost(@Valid @ModelAttribute Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "add-task-form";
        }
        Task task1 = new Task();
        task1.setTaskName(task.getTaskName());
        task1.setIssueDate(task.getIssueDate());
        task1.setCompletionDate(task.getCompletionDate());
        task1.setStatus(TaskStatus.INPROGRESS);

        Task savedTask = taskRepo.save(task1);

        System.out.println("SAVED TASK ID IS: "+savedTask.getId());
        return "redirect:/task/post";
    }


}
