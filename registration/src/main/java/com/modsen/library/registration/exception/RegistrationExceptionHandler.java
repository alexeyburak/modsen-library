package com.modsen.library.registration.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.modsen.common.exception.CommonExceptionResolver;
import com.modsen.common.exception.ErrorResponse;
import com.modsen.common.exception.GlobalErrorCode;
import com.modsen.common.exception.LibraryGlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.badRequest;

@ControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler(LibraryGlobalException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(LibraryGlobalException ex) {
        return CommonExceptionResolver.handleGlobalException(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        return CommonExceptionResolver.handleValidationErrors(ex);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorResponse> handleJWTVerificationException(JWTVerificationException ex) {
        return badRequest()
                .body(ErrorResponse.builder()
                        .code(GlobalErrorCode.GLOBAL_EXCEPTION)
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }

}
