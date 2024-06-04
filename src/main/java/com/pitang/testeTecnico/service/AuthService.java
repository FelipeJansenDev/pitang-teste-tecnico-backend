package com.pitang.testeTecnico.service;

import com.pitang.testeTecnico.config.TokenService;
import com.pitang.testeTecnico.mapper.UsuarioMapper;
import com.pitang.testeTecnico.model.Usuario;
import com.pitang.testeTecnico.model.dto.LoginResponseDto;
import com.pitang.testeTecnico.model.dto.LoginUserDTO;
import com.pitang.testeTecnico.model.dto.MeDTO;
import com.pitang.testeTecnico.repository.UsuarioRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private AuthenticationManager authenticationManager;

    private final ApplicationContext context;

    private final TokenService tokenService;

    private final UsuarioMapper usuarioMapper;

    public AuthService(UsuarioRepository usuarioRepository, ApplicationContext context, TokenService tokenService, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.context = context;
        this.tokenService = tokenService;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario loadUserByUsername(String login) throws UsernameNotFoundException {

        if (!usuarioRepository.existsByLogin(login)) {
            throw new UsernameNotFoundException(login);
        }
        return usuarioRepository.findByLogin(login);
    }

    public LoginResponseDto login(LoginUserDTO loginUserDTO){
        authenticationManager = context.getBean(AuthenticationManager.class);
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginUserDTO.getLogin(), loginUserDTO.getPassword());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        updateLastLogin(loginUserDTO.getLogin());
        return new LoginResponseDto(tokenService.generateToken((Usuario) auth.getPrincipal()));
    }

    private void updateLastLogin(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        usuario.setLastLogin(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }

    public MeDTO me() {
        return usuarioMapper.toMe((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
