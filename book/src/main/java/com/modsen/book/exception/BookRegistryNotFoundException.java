package com.modsen.book.exception;

import com.modsen.common.exception.LibraryGlobalException;

public class BookRegistryNotFoundException extends LibraryGlobalException {
    public BookRegistryNotFoundException(String message) {
        super(message);
    }
}
