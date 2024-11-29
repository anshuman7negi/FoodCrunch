package com.samyati.user_management.controller;

import com.samyati.user_management.dto.LoginDto;
import com.samyati.user_management.dto.ResponseDto;
import com.samyati.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginDto loginRequest) {
        return userService.getLogin(loginRequest);
    }

    @PostMapping("/register")
    public ResponseDto registerUser(@RequestBody LoginDto loginRequest) {
        return userService.getRegisterUser(loginRequest);
    }

    @PostMapping("/changePassword")
    public ResponseDto changePassword(@RequestBody LoginDto loginRequest) {
        return userService.getChangePassword(loginRequest);
    }

    @PostMapping("/forgetPassword")
    public ResponseDto postMethodName(@RequestBody LoginDto loginDto) {
        System.out.println("--------------------inside controller");
        return userService.getForgetPassword(loginDto);
    }
    
    
}
