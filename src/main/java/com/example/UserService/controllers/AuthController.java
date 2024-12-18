package com.example.UserService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.dtos.SignUpRequestDto;
import com.example.UserService.dtos.UserDto;
import com.example.UserService.dtos.ValidateTokenRequestDto;
import com.example.UserService.models.SessionStatus;
import com.example.UserService.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private AuthService authService;

    public AuthController( AuthService authService){
        this.authService = authService;
    }

    
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        UserDto userDto = authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> logIn(@RequestBody SignUpRequestDto signUpRequestDto){
       return authService.logIn(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        return new ResponseEntity<>(authService
        .validate(validateTokenRequestDto.getToken(), validateTokenRequestDto.getUserId()), HttpStatus.OK);
    }

}
