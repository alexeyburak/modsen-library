package com.modsen.book.service;

import com.modsen.book.dto.BookRegistryUploadDTO;
import com.modsen.book.model.Book;
import com.modsen.book.model.BookRegistry;

import java.util.List;
import java.util.UUID;

public interface BookRegistryService {
    List<Book> findAllFreeBooks();

    List<BookRegistry> findAll();

    BookRegistry save(BookRegistryUploadDTO book);

    void delete(UUID id);

    BookRegistry findById(UUID id);
}
