package com.pitang.testeTecnico.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pitang.testeTecnico.config.TokenService;
import com.pitang.testeTecnico.mapper.UsuarioMapper;
import com.pitang.testeTecnico.model.Usuario;
import com.pitang.testeTecnico.model.dto.LoginUserDTO;
import com.pitang.testeTecnico.model.dto.MeDTO;
import com.pitang.testeTecnico.model.dto.UsuarioDTO;
import com.pitang.testeTecnico.repository.UsuarioRepository;
import com.pitang.testeTecnico.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @Mock
    private MeDTO meDTO;

    @Mock
    private UsuarioMapper usuarioMapper;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Mock
    Usuario usuario;

    @Mock
    UsuarioDTO usuarioDTO;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    Authentication authentication;

    private String email = "felipe.jansen@outlook.com";

    @Test
    public void testMe() throws Exception {
        // ARRANGE
        when(tokenService.validateToken(anyString())).thenReturn(email);
        when(usuarioRepository.findByEmail(anyString())).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDTO);

        // ACT
        mockMvc.perform(MockMvcRequestBuilders.get("/me")
                        .header("Authorization", "TokenHere"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testSignin() throws Exception {

        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setLogin("felipeJansen");
        loginUserDTO.setPassword("123456");

        // ARRANGE
        when(tokenService.validateToken(anyString())).thenReturn(email);
        when(usuarioRepository.findByEmail(anyString())).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDTO);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(usuarioRepository.findByLogin(loginUserDTO.getLogin())).thenReturn(usuario);
        when(authentication.getPrincipal()).thenReturn(usuario);

        // ACT
        mockMvc.perform(MockMvcRequestBuilders.post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson(loginUserDTO))
                        .header("Authorization", "TokenHere"))
                .andExpect(status().isOk())
                .andReturn();
    }

    private String getJson(LoginUserDTO loginUserDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(loginUserDTO);
        return requestJson;
    }

}