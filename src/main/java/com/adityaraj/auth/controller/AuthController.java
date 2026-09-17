package com.adityaraj.auth.controller;

import com.adityaraj.auth.dto.AuthResponse;
import com.adityaraj.auth.dto.LogInRequest;
import com.adityaraj.auth.dto.SignUpRequest;
import com.adityaraj.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody SignUpRequest request
            ){
        AuthResponse authResponse = authService.register(request);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LogInRequest request
    ){
        AuthResponse authResponse= authService.login(request);
        return ResponseEntity.ok(authResponse);
    }

}
