package pl.edu.wat.projektai.ai.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.wat.projektai.ai.dto.ApiError;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, HttpServletResponse response) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Nieprawidłowy login lub hasło.");
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}
