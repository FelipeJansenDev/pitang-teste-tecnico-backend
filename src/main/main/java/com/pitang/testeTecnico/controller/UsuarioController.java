package com.pitang.testeTecnico.controller;

import com.pitang.testeTecnico.model.Usuario;
import com.pitang.testeTecnico.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios()  {
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

}
