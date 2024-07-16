package com.example.messagemanagementsystem.controller;

import com.example.messagemanagementsystem.service.abstracts.AuthService;
import com.example.messagemanagementsystem.service.dto.auth.LoginRequest;
import com.example.messagemanagementsystem.service.dto.auth.RegisterRequest;
import com.example.messagemanagementsystem.service.dto.auth.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("login")
    public String login(LoginRequest request){
        return authService.login(request);
    }
    @PostMapping
    public RegisterResponse register(RegisterRequest request){
        return authService.register(request);
    }

}
