package com.SpendControl.maxwell.SpendControl.controller;

import com.SpendControl.maxwell.SpendControl.dto.UserDto;
import com.SpendControl.maxwell.SpendControl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        try {
            UserDto userCreated = userService.createUser(user);
            URI uri = URI.create("/user/" + userCreated.getId());
            return ResponseEntity.created(uri).body(userCreated);
        }catch (IllegalArgumentException iaex){
            logger.warn("Data validation failed. "+ iaex.getMessage(),iaex );
            return ResponseEntity.badRequest().body(iaex.getMessage());
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return  ResponseEntity.internalServerError().body("Ocorreu um erro no processamento de sua solicitação, " +
                    "por favor entre em contato com o seu suporte técnico");
        }
    }

    @GetMapping
    public ResponseEntity<?> findUsers(@RequestParam(required = false) String name) {
        try {
            List<UserDto> users = userService.findUsers(name);
            if(users.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(users);
            }

        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return  ResponseEntity.internalServerError().body("Ocorreu um erro no processamento de sua solicitação, " +
                    "por favor entre em contato com o seu suporte técnico");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findUserByID(@PathVariable Long id) {
        try{
            UserDto user =  userService.findUserByID(id);
            return ResponseEntity.ok(user);
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return  ResponseEntity.internalServerError().body("Ocorreu um erro no processamento de sua solicitação, " +
                    "por favor entre em contato com o seu suporte técnico");
        }
    }
//    @PatchMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUser){
//
//    }

}
