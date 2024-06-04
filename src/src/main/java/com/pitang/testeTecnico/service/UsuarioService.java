package com.pitang.testeTecnico.service;

import com.pitang.testeTecnico.mapper.UsuarioMapper;
import com.pitang.testeTecnico.model.Usuario;
import com.pitang.testeTecnico.model.dto.UsuarioDTO;
import com.pitang.testeTecnico.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDTO> getAll() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
    }

    public Usuario createUsuario(UsuarioDTO usuarioDTO) {
        return usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));
    }

    public UsuarioDTO getUsuarioById(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDto).orElse(null);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) throws Exception {

        if (!usuarioRepository.existsById(id)) {
            throw new Exception("Não encontrado", null);
        }
        return usuarioRepository.save(usuario);
    }
}
