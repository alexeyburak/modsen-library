package com.modsen.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LibraryGlobalException extends RuntimeException {

    private final String code;
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public LibraryGlobalException(String message) {
        super(message);
        this.message = message;
        this.code = GlobalErrorCode.GLOBAL_EXCEPTION;
    }

    public LibraryGlobalException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

}
