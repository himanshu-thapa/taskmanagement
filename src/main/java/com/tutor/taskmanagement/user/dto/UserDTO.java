package com.tutor.taskmanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotNull
    @Size(min = 2, message = "Size must be greater than 2")
    private String firstName;
    @NotNull
    @Size(min = 2, message = "Size must be greater than 2")
    private String lastName;
    @Email(message = "must be a valid email.")
    private String email;
    @NotNull
    @Size(min = 5, max = 12, message = "Password must be min 5 char and max 12 char")
    private String password;
    private String phone;
}
