package com.tutor.taskmanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDto {
    private String password;
    private String confirmPassword;
    private String token;
}
