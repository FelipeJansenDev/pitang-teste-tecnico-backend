package com.pitang.testeTecnico.config;

import com.pitang.testeTecnico.exceptions.EmailExistenteException;
import com.pitang.testeTecnico.exceptions.LicensePlateExistenteException;
import com.pitang.testeTecnico.exceptions.LoginExistenteException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControlExceptionHandler {

    private final MessageSource messageSource;

    public ControlExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("recurso.nao-existente", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 1));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ EmailExistenteException.class })
    public ResponseEntity<Object> handleNonexistentOrInactivePersonException(EmailExistenteException ex) {
        String userMessage = messageSource.getMessage("recurso.email-existente", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 2));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ LoginExistenteException.class })
    public ResponseEntity<Object> handleLoginExistenteException(LoginExistenteException ex) {
        String userMessage = messageSource.getMessage("recurso.login-existente", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 3));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ BadCredentialsException.class })
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        String userMessage = messageSource.getMessage("seguranca.credenciais-incorretas", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 4));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ LicensePlateExistenteException.class })
    public ResponseEntity<Object> handleLicensePlateExistenteException(LicensePlateExistenteException ex) {
        String userMessage = messageSource.getMessage("recurso.licenseplate-existente", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 5));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        List<Error> errors = criarListaDeErros(ex.getBindingResult());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private List<Error> criarListaDeErros(BindingResult bindingResult) {
        List<Error> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.getCode();
            erros.add(new Error(mensagemUsuario, 6));
        }

        return erros;
    }

    public static class Error {

        private String message;
        private Integer errorCode;

        public Error(String message, Integer errorCode) {
            super();
            this.message = message;
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

    }


}