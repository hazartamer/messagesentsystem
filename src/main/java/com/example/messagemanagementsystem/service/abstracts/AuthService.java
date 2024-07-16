package com.example.messagemanagementsystem.service.abstracts;

import com.example.messagemanagementsystem.service.dto.auth.LoginRequest;
import com.example.messagemanagementsystem.service.dto.auth.RegisterRequest;
import com.example.messagemanagementsystem.service.dto.auth.RegisterResponse;

public interface AuthService {
    String login(LoginRequest request);

    RegisterResponse register(RegisterRequest request);
}
