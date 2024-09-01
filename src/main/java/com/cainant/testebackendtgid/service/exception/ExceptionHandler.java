package com.cainant.testebackendtgid.service.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handle404() {
        return ResponseEntity.notFound().build();
    }

    private record ValidationDataError(String field, String error) {
        ValidationDataError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationDataError>> handle400(MethodArgumentNotValidException e) {

        var erros = e.getFieldErrors();
        return ResponseEntity.badRequest()
                .body(erros.stream()
                        .map(ValidationDataError::new).toList());
    }
}