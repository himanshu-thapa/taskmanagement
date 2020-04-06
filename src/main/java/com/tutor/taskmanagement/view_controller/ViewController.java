package com.tutor.taskmanagement.view_controller;

import com.tutor.taskmanagement.task.dao.TaskDAO;
import com.tutor.taskmanagement.task.dto.TaskDTO;
import com.tutor.taskmanagement.task.entities.Task;
import com.tutor.taskmanagement.task.entities.enums.TaskStatus;
import com.tutor.taskmanagement.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private TaskDAO taskDAO;

    //    @RequestMapping("/")
    @GetMapping({"/", "/home"})
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView("index");
        /*Find all tasks*/
        List<Task> tasks = taskDAO.findAll();
        mv.addObject("tasks", tasks);
        return mv;
    }

    @GetMapping("/task/add")
    public ModelAndView showAddTaskForm() {
        ModelAndView mv = new ModelAndView("add-task-form");
        mv.addObject("task", new Task());
        return mv;
    }

    @PostMapping("/task/post")
    public String createNewPost(@Valid @ModelAttribute TaskDTO task, BindingResult result) {
        if (result.hasErrors()) {
            return "add-task-form";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");

        Task task1 = new Task();
        task1.setTaskName(task.getTaskName());

        try {
            Date issueDate = sdf.parse(task.getIssueDate());
            Date completionDate = sdf.parse(task.getCompletionDate());

            task1.setIssueDate(issueDate);
            task1.setCompletionDate(completionDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        task1.setStatus(TaskStatus.INPROGRESS);

        Task savedTask = taskRepo.save(task1);

        System.out.println("SAVED TASK ID IS: " + savedTask.getId());
        return "redirect:/home";
    }

    @GetMapping("/task/update/{id}")
    public ModelAndView updateTask(@PathVariable Long id) {
        Task task = taskDAO.findById(id);
        ModelAndView mv = new ModelAndView("add-task-form");
        mv.addObject("task", task);
        return mv;
    }


}
