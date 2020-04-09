package com.tutor.taskmanagement.user.controller;

import com.tutor.taskmanagement.user.UserMapper;
import com.tutor.taskmanagement.user.dao.UserDAO;
import com.tutor.taskmanagement.user.dto.UserDTO;
import com.tutor.taskmanagement.user.enitites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserMapper mapper;

    // /users -> list all users GET
    @GetMapping("/users")
    public ModelAndView usersHomePage() {
        ModelAndView mv = new ModelAndView("");
        List<User> users = userDAO.findAllUsers();
        mv.addObject("users", users);
        mv.addObject("userDTO", new UserDTO());
        return mv;
    }

    // /users -> post user POST
    @PostMapping("/users")
    public String createNewUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-user-form";
        }

        User user = mapper.convertToEntity(userDTO);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        userDAO.createUser(user);
        return "redirect:/users";
    }


    // /users/update/{id} -> update
    @PutMapping("/users")
    public String updateUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-user-form";
        }

        User user = userDAO.findUserById(userDTO.getId());
        if (user != null) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setPhone(userDTO.getPhone());
            user.setUpdatedDate(new Date());
        }
        return "redirect:/users";
    }

    // /users/delete/{id} -> delete
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDAO.deleteUser(id);
    }
}
