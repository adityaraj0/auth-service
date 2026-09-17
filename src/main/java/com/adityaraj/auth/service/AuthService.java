package com.adityaraj.auth.service;

import com.adityaraj.auth.dto.AuthResponse;
import com.adityaraj.auth.dto.LogInRequest;
import com.adityaraj.auth.dto.SignUpRequest;

public interface AuthService {
    AuthResponse register(SignUpRequest request);

    AuthResponse login(LogInRequest request);
}
