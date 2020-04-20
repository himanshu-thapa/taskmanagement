package com.tutor.taskmanagement.user.controller;

import com.tutor.taskmanagement.user.dao.UserDAO;
import com.tutor.taskmanagement.user.dto.EmailDto;
import com.tutor.taskmanagement.user.enitites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class ForgetPasswordController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/forget-password")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("forgot-password");
        mv.addObject("emailDto", new EmailDto());
        return mv;
    }

    @PostMapping("/forget-password")
    public ModelAndView resetPassword(@ModelAttribute EmailDto emailDto) {
        /*Check if email is valid
         * valid -> generate a random token
         *       -> build password reset url and send it as email
         * invalid -> warning, email is not valid*/
        System.out.println("EMAIL FOR PASSWORD RESET: " + emailDto.getEmail());
        ModelAndView modelAndView = new ModelAndView("forgot-password");

        User user = userDAO.findUserByEmail(emailDto.getEmail());
        if (user == null) {
            System.out.println("USER NOT FOUND");
            modelAndView.addObject("errorMessage",
                    "No user found with this email. Please check!");
            return modelAndView;
        }

        String resetToken = UUID.randomUUID().toString();
        System.out.println("RESET TOKEN IS: " + resetToken);
        user.setResetToken(resetToken);
        userDAO.createUser(user);

        modelAndView.addObject("successMessage", "A password reset link has been sent to " + user.getEmail());
        return modelAndView;
    }

}
