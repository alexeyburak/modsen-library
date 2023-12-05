package com.modsen.book.service;

import com.modsen.book.dto.BookUpdateDTO;
import com.modsen.book.dto.BookUploadDTO;
import com.modsen.book.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<Book> findAll();

    Book findById(UUID id);

    Book findByIsbn(String isbn);

    Book save(BookUploadDTO book);

    Book update(UUID id, BookUpdateDTO book);

    void delete(UUID id);
}
