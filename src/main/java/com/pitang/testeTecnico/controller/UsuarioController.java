package com.pitang.testeTecnico.controller;

import com.pitang.testeTecnico.model.dto.UsuarioDTO;
import com.pitang.testeTecnico.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Método para retornar todos os usuários cadastrados no sistema.
     * Obs.: Não precisa de autenticação.
     * @return Lista de todos os usuários cadastrados
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsuarios()  {
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    /**
     * Retornar usuário específico pelo ID.
     * @param id id do usuário cadastrado
     * @return UsuarioDTO preenchido com as informações do usuário solicitado
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id)  {
        return new ResponseEntity<>(usuarioService.getUsuarioById(id), HttpStatus.OK);
    }

    /**
     * Método para solicitar criação de um novo usuário
     * @param usuario Objeto com as informações do usuário que deve ser criado
     * @return Usuário criado e com respectivo ID setado, além de status HTTP como "CREATED"
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuario)  {
        return new ResponseEntity<>(usuarioService.createUsuario(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(
            @Valid @RequestBody UsuarioDTO usuarioDTO,
            @PathVariable Long id
            ) {
        return new ResponseEntity<>(usuarioService.updateUsuario(id, usuarioDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id)  {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
