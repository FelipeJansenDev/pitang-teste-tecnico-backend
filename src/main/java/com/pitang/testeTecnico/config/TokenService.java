package com.pitang.testeTecnico.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pitang.testeTecnico.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private String secret = "tokenPitang";

    public String generateToken(Usuario userModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                .withIssuer("auth")
                .withSubject(userModel.getEmail())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);


        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro para gerar token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                .withIssuer("auth")
                .build()
                .verify(token)
                .getSubject();
        }
        catch (JWTVerificationException exception) {
            return "";
        }
    }

        private Instant getExpirationDate(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        }
    }