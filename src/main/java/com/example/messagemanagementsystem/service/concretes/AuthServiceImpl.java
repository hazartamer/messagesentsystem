package com.example.messagemanagementsystem.service.concretes;

import com.example.messagemanagementsystem.entity.User;
import com.example.messagemanagementsystem.repository.UserRepository;
import com.example.messagemanagementsystem.security.JwtService;
import com.example.messagemanagementsystem.service.abstracts.AuthService;
import com.example.messagemanagementsystem.service.dto.auth.LoginRequest;
import com.example.messagemanagementsystem.service.dto.auth.RegisterRequest;
import com.example.messagemanagementsystem.service.dto.auth.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;



        public String login(LoginRequest request) {
            User user = userRepository.findByEmail(request.getEmail());



            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            if(!authentication.isAuthenticated()){
                throw new RuntimeException("E-posta ya da şifre yanlıştır.");
            }
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("UserId", user.getId());
            extraClaims.put("UserName", user.getFullName());
            extraClaims.put("UserEmail", user.getEmail());
            extraClaims.put("UserRole", user.getRole());
            return jwtService.generateToken(user.getUsername(), extraClaims);
        }

        public RegisterResponse register(RegisterRequest request) {
            User user = new User();
            user.setFullName(request.getFullName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);


            RegisterResponse response = new RegisterResponse(user.getFullName());
            return response;
        }
        //Business Rules


    }


