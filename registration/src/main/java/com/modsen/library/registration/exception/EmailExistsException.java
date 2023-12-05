package com.modsen.library.registration.exception;

import com.modsen.common.exception.LibraryGlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailExistsException extends LibraryGlobalException {
    public EmailExistsException(String message) {
        super(message);
    }
}
