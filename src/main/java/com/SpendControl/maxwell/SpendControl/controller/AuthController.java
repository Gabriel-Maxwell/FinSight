package com.SpendControl.maxwell.SpendControl.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpendControl.maxwell.SpendControl.dto.UserDto;
import com.SpendControl.maxwell.SpendControl.mapper.UserMapper;
import com.SpendControl.maxwell.SpendControl.service.AuthenticationService;
import com.SpendControl.maxwell.SpendControl.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AuthController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        UserDto userCreated = userService.createUser(user);
        URI uri = URI.create("/api/user/" + userCreated.getId());
        return ResponseEntity.created(uri).body(userCreated);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        return ResponseEntity.ok(authenticationService.authenticate(UserMapper.toDomain(user))); 
    }
}
