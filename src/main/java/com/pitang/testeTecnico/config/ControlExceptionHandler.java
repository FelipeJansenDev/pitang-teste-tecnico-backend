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

/**
 * Método responsável por tratar todas as exceções que ocorrem no sistema
 */
@ControllerAdvice
public class ControlExceptionHandler {

    private final MessageSource messageSource;

    public ControlExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Método para tratar a exceção caso algum carro ou usuário não seja encontrado no sistema
     * @param ex Erro acionado do tipo EmptyResultDataAccessException
     * @param request requisição
     * @return
     */
    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("recurso.nao-existente", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 1));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Método para tratar a exceção caso algum e-mail de usuário já esteja sendo utilizado
     * @param ex Erro acionado do tipo EmailExistenteException
     * @return
     */
    @ExceptionHandler({ EmailExistenteException.class })
    public ResponseEntity<Object> handleNonexistentOrInactivePersonException(EmailExistenteException ex) {
        String userMessage = messageSource.getMessage("recurso.email-existente", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 2));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Método para tratar a exceção caso algum login já esteja sendo utilizado
     * @param ex Erro acionado do tipo LoginExistenteException
     * @return
     */
    @ExceptionHandler({ LoginExistenteException.class })
    public ResponseEntity<Object> handleLoginExistenteException(LoginExistenteException ex) {
        String userMessage = messageSource.getMessage("recurso.login-existente", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 3));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Método para tratar a exceção caso as credenciais de login estejam incorretas
     * @param ex Erro acionado do tipo BadCredentialsException
     * @return
     */
    @ExceptionHandler({ BadCredentialsException.class })
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        String userMessage = messageSource.getMessage("seguranca.credenciais-incorretas", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 4));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Método para tratar a exceção caso algum carro seja cadastrado com uma placa que já existe no sistema
     * @param ex Erro acionado do tipo LicensePlateExistenteException
     * @return
     */
    @ExceptionHandler({ LicensePlateExistenteException.class })
    public ResponseEntity<Object> handleLicensePlateExistenteException(LicensePlateExistenteException ex) {
        String userMessage = messageSource.getMessage("recurso.licenseplate-existente", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 5));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Método para tratar a exceção caso alguma informação no objeto de carro ou usuário esteja inválido
     * @param ex Erro acionado do tipo MethodArgumentNotValidException
     * @param request requisição
     * @return
     */
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