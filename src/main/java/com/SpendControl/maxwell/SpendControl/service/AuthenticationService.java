package com.SpendControl.maxwell.SpendControl.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.SpendControl.maxwell.SpendControl.domain.User;

@Service
public class AuthenticationService {
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public AuthenticationService(AuthenticationManager authManager, JWTService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public String authenticate(User user) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return jwtService.generateToken(auth);
    }

    public Authentication authenticateApp(User user) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return auth;
    }
}
