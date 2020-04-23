package com.tutor.taskmanagement.user.controller;

import com.tutor.taskmanagement.user.dao.UserDAO;
import com.tutor.taskmanagement.user.dto.EmailDto;
import com.tutor.taskmanagement.user.dto.PasswordDto;
import com.tutor.taskmanagement.user.dto.SuccessHandler;
import com.tutor.taskmanagement.user.enitites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Controller
@RequestMapping("/forget-password")
public class ForgetPasswordController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("forgot-password");
        mv.addObject("emailDto", new EmailDto());
        return mv;
    }

    @PostMapping
    public ModelAndView resetPassword(@ModelAttribute EmailDto emailDto, HttpServletRequest request) throws MessagingException {
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

        /*Build url*/
        String appUrl = request.getScheme() + "://" + request.getServerName();

        /*Send reset token in email*/
        sendEmail(resetToken, user.getEmail(), appUrl);


        modelAndView.addObject("successMessage", "A password reset link has been sent to " + user.getEmail());
        return modelAndView;
    }

    private void sendEmail(String resetToken, String email, String appUrl) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setTo(email);

        helper.setText("To reset your password,click the link below:\n" + appUrl + ":9090/forget-password/reset?token="
                + resetToken, true);
        helper.setSubject("Password reset");

        emailSender.send(message);
        System.out.println("MAIL SENT SUCCESSFULLY TO: " + email);
    }

    //http://localhost:9090/forgetPassword/reset?token=jhfjkabfajkga-jgnakjgnba

    @GetMapping("/reset")
    public ModelAndView resetPasswordForm(@RequestParam String token) {
        User user = userDAO.findUserByResetToken(token);
        if (token == null || user == null) {
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("errorMessage", "No Token found!");
            return mv;
        }

        ModelAndView mv = new ModelAndView("reset-password");
        mv.addObject("token", token);
        mv.addObject("passwordDto", new PasswordDto());
        return mv;
    }

    @PostMapping("/reset")
    @ResponseBody
    public SuccessHandler resetPassword(@RequestBody PasswordDto passwordDto) {
        User user = userDAO.findUserByResetToken(passwordDto.getToken());
        if (user != null) {
            user.setPassword(encoder.encode(passwordDto.getPassword()));
            userDAO.updateUser(user);
        }
        return new SuccessHandler(200, "changed!");
    }

}
