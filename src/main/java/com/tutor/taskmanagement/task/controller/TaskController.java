package com.tutor.taskmanagement.task.controller;

import com.tutor.taskmanagement.task.TaskMapper;
import com.tutor.taskmanagement.task.dao.TaskDAO;
import com.tutor.taskmanagement.task.dto.TaskDTO;
import com.tutor.taskmanagement.task.entities.Task;
import com.tutor.taskmanagement.user.enitites.User;
import com.tutor.taskmanagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/home")
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView("index");
        /*Find all tasks*/
        List<Task> tasks = taskDAO.findAll();
        /*Get Username*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepo.findByEmail(email);
        mv.addObject("username", user.getFirstName() + ' ' + user.getLastName());
        mv.addObject("tasks", tasks);
        return mv;
    }

    @GetMapping("/task/add")
    public ModelAndView showAddTaskForm() {
        ModelAndView mv = new ModelAndView("add-task-form");
        mv.addObject("taskDTO", new TaskDTO());
        return mv;
    }

    @PostMapping("/task/post")
    public String createNewPost(@Valid TaskDTO taskDTO, BindingResult result) throws ParseException {
        if (result.hasErrors()) {
            return "add-task-form";
        }

        /*id==null
         * create new task
         * id !=null
         * update task*/

        if (taskDTO.getId() == null) {
            //Create
            Task task = taskMapper.convertToEntity(taskDTO);
//            task.setStatus(TaskStatus.INPROGRESS);
            task.setStatus(taskDTO.getStatus());

            Task savedTask = taskDAO.save(task);
            System.out.println("SAVED TASK ID IS: " + savedTask.getId());
        } else {
            //update
            Task task = taskDAO.findById(taskDTO.getId());
            task.setTaskName(taskDTO.getTaskName());
            task.setIssueDate(taskDTO.getIssueDateConverted());
            task.setCompletionDate(taskDTO.getCompletionDateConverted());
            task.setStatus(taskDTO.getStatus());
            Task updatedTask = taskDAO.save(task);
            System.out.println("UPDATED TASK ID IS: " + updatedTask.getId());
        }
        return "redirect:/home";
    }

    @GetMapping("/task/update/{id}")
    public ModelAndView updateTask(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("add-task-form");

        /*Convert entity to DTO*/
        Task task = taskDAO.findById(id);
        TaskDTO taskDTO = taskMapper.convertToDTO(task);
        mv.addObject("taskDTO", taskDTO);

        return mv;
    }

    @DeleteMapping("/task/delete/{id}")
    @ResponseBody
    public String deleteTask(@PathVariable Long id) {
        taskDAO.deleteTask(id);
        return "OK";
    }
}
