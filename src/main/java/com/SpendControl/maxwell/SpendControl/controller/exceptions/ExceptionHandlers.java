package com.SpendControl.maxwell.SpendControl.controller.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidData(IllegalArgumentException iaex){
        logger.warn("Data validation failed. "+ iaex.getMessage(),iaex );
        return ResponseEntity.badRequest()
            .body(iaex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        logger.error(ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Credências inválidas");
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> handleUnathorizedException(AuthorizationDeniedException ex) {
        logger.error(ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        logger.error(ex.getMessage(),ex);
        return ResponseEntity.internalServerError()
            .body("Ocorreu um erro no processamento de sua solicitação, por favor entre em contato com o seu suporte técnico");
    }
}
