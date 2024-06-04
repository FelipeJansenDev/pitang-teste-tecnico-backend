package com.pitang.testeTecnico.controller;

import com.pitang.testeTecnico.model.dto.LoginResponseDto;
import com.pitang.testeTecnico.model.dto.LoginUserDTO;
import com.pitang.testeTecnico.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDTO loginUserDTO){
        return new ResponseEntity<>(authService.login(loginUserDTO), HttpStatus.OK);
    }

}
