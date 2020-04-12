package com.tutor.taskmanagement.user.controller;

import com.tutor.taskmanagement.user.UserMapper;
import com.tutor.taskmanagement.user.dao.UserDAO;
import com.tutor.taskmanagement.user.dto.UserDTO;
import com.tutor.taskmanagement.user.enitites.Role;
import com.tutor.taskmanagement.user.enitites.User;
import com.tutor.taskmanagement.user.repository.RoleRepository;
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
    @Autowired
    private RoleRepository roleRepo;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView registrationPage() {
        ModelAndView mv = new ModelAndView("user-registration");
        mv.addObject("userDTO", new UserDTO());
        return mv;
    }

    // /users -> list all users GET
    @GetMapping("/users")
    public ModelAndView usersHomePage() {
        ModelAndView mv = new ModelAndView("users");
        List<User> users = userDAO.findAllUsers();
        mv.addObject("users", users);
        return mv;
    }

    @GetMapping("/users/add")
    public ModelAndView addUserForm() {
        ModelAndView mv = new ModelAndView("add-user-form");
        mv.addObject("userDTO", new UserDTO());
        return mv;
    }


    // /users -> post user POST
    @PostMapping("/users")
    public String createNewUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-registration";
        }

        /*Get role*/
        Role role = roleRepo.findByRole("USER");

        User user = mapper.convertToEntity(userDTO);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        user.setRole(role);
        userDAO.createUser(user);
        return "redirect:/login";
    }

    // /users/{id} -> get User GET
    @GetMapping("/users/{id}")
    public ModelAndView userEditPage(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("add-user-form");

        User user = userDAO.findUserById(id);
        UserDTO userDTO = mapper.convertToDTO(user);

        mv.addObject("userDTO", userDTO);
        return mv;
    }


    // /users -> update
    @PutMapping("/users")
    public String updateUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
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
