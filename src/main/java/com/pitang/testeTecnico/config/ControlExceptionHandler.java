package com.pitang.testeTecnico.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ControlExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ControlExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
        List<Error> errors = List.of(new Error(userMessage, 1));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

//    private List<Error> createErrorList(BindingResult bindindResult) {
//        List<Error> errors = new ArrayList<>();
//
//        for (FieldError fieldError : bindindResult.getFieldErrors()) {
//            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
//            String errorCode = fieldError.toString();
//            errors.add(new Error(message, errorCode));
//        }
//
//        return errors;
//    }

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