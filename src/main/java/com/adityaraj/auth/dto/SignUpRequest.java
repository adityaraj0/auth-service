package com.adityaraj.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class SignUpRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is Required")
    private String userName;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 Characters")
    private String password;

}
