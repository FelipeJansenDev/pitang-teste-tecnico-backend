package com.pitang.testeTecnico.service;

import com.pitang.testeTecnico.exceptions.EmailExistenteException;
import com.pitang.testeTecnico.exceptions.LoginExistenteException;
import com.pitang.testeTecnico.mapper.UsuarioMapper;
import com.pitang.testeTecnico.model.Usuario;
import com.pitang.testeTecnico.model.dto.UsuarioDTO;
import com.pitang.testeTecnico.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    public UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    UsuarioMapper usuarioMapper;

    @Mock
    UsuarioDTO usuarioDTO;

    @Mock
    Usuario usuario;

    @Before
    public void setUp() {
        when(usuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDTO);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
    }

    @Test
    @DisplayName("Criação de usuário")
    public void testCreateUsuario() {
        // ARRANGE
        when(usuarioDTO.getEmail()).thenReturn("felipe.jansen@outlook.com");
        when(usuarioRepository.existsByEmail(usuarioDTO.getEmail())).thenReturn(Boolean.FALSE);
        when(usuarioDTO.getPassword()).thenReturn("password123");

        // ACT
        usuarioService.createUsuario(usuarioDTO);
    }

    @Test(expected = EmailExistenteException.class)
    @DisplayName("Criação de usuário quando e-mail já existe")
    public void testCreateUsuarioWhenEmailExists() {
        // ARRANGE
        when(usuarioDTO.getEmail()).thenReturn("felipe.jansen@outlook.com");
        when(usuarioRepository.existsByEmail(usuarioDTO.getEmail())).thenReturn(Boolean.TRUE);

        // ACT
        usuarioService.createUsuario(usuarioDTO);
    }

    @Test(expected = LoginExistenteException.class)
    @DisplayName("Criação de usuário quando login já existe")
    public void testCreateUsuarioWhenLoginExists() {
        // ARRANGE
        when(usuarioDTO.getEmail()).thenReturn("felipe.jansen@outlook.com");
        when(usuarioDTO.getLogin()).thenReturn("felipeJansen");
        when(usuarioRepository.existsByEmail(usuarioDTO.getEmail())).thenReturn(Boolean.FALSE);
        when(usuarioRepository.existsByLogin(usuarioDTO.getLogin())).thenReturn(Boolean.TRUE);

        // ACT
        usuarioService.createUsuario(usuarioDTO);
    }

    @Test
    @DisplayName("Atualização de usuário")
    public void testUpdateUsuario() {
        // ARRANGE
        when(usuarioRepository.existsById(1L)).thenReturn(Boolean.TRUE);

        // ACT
        usuarioService.updateUsuario(1L, usuarioDTO);
    }

}