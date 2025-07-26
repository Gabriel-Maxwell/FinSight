package com.SpendControl.maxwell.SpendControl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpendControl.maxwell.SpendControl.dto.UserDto;
import com.SpendControl.maxwell.SpendControl.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> findUsers(@RequestParam(required = false) String name) {
        List<UserDto> users = userService.findUsers(name);
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(users);
        }
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> findUserByID(@PathVariable Long id) {
        UserDto user =  userService.findUserByID(id);
        return ResponseEntity.ok(user);
    }
//    @PatchMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUser){
//
//    }

}
