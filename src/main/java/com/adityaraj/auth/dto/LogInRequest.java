package com.adityaraj.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class LogInRequest {
    @NotBlank(message = "UserName or Email is required")
    private String identifier;

    @NotBlank(message = "Password is required")
    private String password;
}
