package com.adityaraj.auth.service.impl;

import com.adityaraj.auth.dto.AuthResponse;
import com.adityaraj.auth.dto.LogInRequest;
import com.adityaraj.auth.dto.SignUpRequest;
import com.adityaraj.auth.entity.User;
import com.adityaraj.auth.entity.enums.Role;
import com.adityaraj.auth.repository.UserRepository;
import com.adityaraj.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse register(SignUpRequest request) {

        boolean userNameEmpty =
                request.getUserName() == null ||
                request.getUserName().isBlank();

        if (userNameEmpty) {
            throw new RuntimeException("Username is empty");
        }

        boolean emailEmpty =
                request.getEmail() ==  null ||
                request.getEmail().isBlank();

        if (emailEmpty) {
            throw new RuntimeException("Email is required");
        }

        boolean passwordEmpty =
                request.getPassword() == null ||
                request.getPassword().isBlank();

        if (passwordEmpty) {
            throw new RuntimeException("Password is required");
        }

        if(userRepository.existsByUserName(request.getUserName())){
            throw new RuntimeException("Username already exists");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user.setRole(Role.USER);
        user.setActive(true);

        User savedUser = userRepository.save(user);

        return generateAuthResponse(savedUser);
    }

    @Override
    public AuthResponse login(LogInRequest request) {

        String identifier = request.getIdentifier();

        if (identifier == null || identifier.isBlank()) {
            throw new RuntimeException("UserName or Email is required");
        }

        if(request.getPassword() == null || request.getPassword().isBlank()){
            throw new RuntimeException("Password is required");
        }

        User user = userRepository
                .findByEmailOrUserName(identifier, identifier)
                .orElseThrow(()-> new RuntimeException(
                        "Invalid UserName or Email"
                ));

        if(!user.getActive()){
            throw new RuntimeException("User is inactive");
        }

        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        return generateAuthResponse(user);
    }

    private AuthResponse generateAuthResponse(User user) {

        AuthResponse authResponse = new AuthResponse();

        authResponse.setUserId(user.getId());
        authResponse.setName(user.getName());
        authResponse.setUsername(user.getUserName());
        authResponse.setEmail(user.getEmail());
        authResponse.setRole(user.getRole().name());

        return authResponse;
    }

}
