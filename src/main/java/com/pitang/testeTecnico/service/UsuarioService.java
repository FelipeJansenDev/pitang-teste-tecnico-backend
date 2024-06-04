package com.pitang.testeTecnico.service;

import com.pitang.testeTecnico.config.TokenService;
import com.pitang.testeTecnico.exceptions.EmailExistenteException;
import com.pitang.testeTecnico.exceptions.LoginExistenteException;
import com.pitang.testeTecnico.mapper.UsuarioMapper;
import com.pitang.testeTecnico.model.Usuario;
import com.pitang.testeTecnico.model.dto.UsuarioDTO;
import com.pitang.testeTecnico.repository.UsuarioRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, ApplicationContext context, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDTO> getAll() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
    }

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new EmailExistenteException();
        }

        if (usuarioRepository.existsByLogin(usuarioDTO.getLogin())) {
            throw new LoginExistenteException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDTO.getPassword());
        usuarioDTO.setPassword(encryptedPassword);

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setCreatedAt(LocalDateTime.now());

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    public UsuarioDTO getUsuarioById(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDto).orElse(null);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        if (!usuarioRepository.existsById(id)) {
            throw new EmptyResultDataAccessException(Math.toIntExact(id));
        }
        usuarioDTO.setId(id);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

}
