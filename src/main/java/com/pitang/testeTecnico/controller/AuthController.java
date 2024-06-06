package com.pitang.testeTecnico.controller;

import com.pitang.testeTecnico.model.dto.LoginResponseDto;
import com.pitang.testeTecnico.model.dto.LoginUserDTO;
import com.pitang.testeTecnico.model.dto.MeDTO;
import com.pitang.testeTecnico.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Método mara realziar login
     * @param loginUserDTO objeto contendo informações de login e senha
     * @return retorna um Token JWT
     */
    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginUserDTO loginUserDTO){
        return new ResponseEntity<>(authService.login(loginUserDTO), HttpStatus.OK);
    }

    /**
     * Retorna as informações do usuário logado no momento através
     * @return objeto contendo as informações do usuário logado
     */
    @GetMapping("/me")
    public ResponseEntity<MeDTO> me(){
        return new ResponseEntity<>(authService.me(), HttpStatus.OK);
    }



}
