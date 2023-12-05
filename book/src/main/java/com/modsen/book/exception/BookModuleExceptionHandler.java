package com.modsen.book.exception;

import com.modsen.common.exception.CommonExceptionResolver;
import com.modsen.common.exception.ErrorResponse;
import com.modsen.common.exception.LibraryGlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class BookModuleExceptionHandler {

    @ExceptionHandler(LibraryGlobalException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(LibraryGlobalException ex) {
        return CommonExceptionResolver.handleGlobalException(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        return CommonExceptionResolver.handleValidationErrors(ex);
    }

}
