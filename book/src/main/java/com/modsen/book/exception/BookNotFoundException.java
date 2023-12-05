package com.modsen.book.exception;

import com.modsen.common.exception.LibraryGlobalException;

public class BookNotFoundException extends LibraryGlobalException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
