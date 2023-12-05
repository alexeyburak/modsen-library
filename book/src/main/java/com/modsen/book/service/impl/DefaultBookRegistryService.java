package com.modsen.book.service.impl;

import com.modsen.book.dto.BookRegistryUploadDTO;
import com.modsen.book.exception.BookRegistryNotFoundException;
import com.modsen.book.model.Book;
import com.modsen.book.model.BookRegistry;
import com.modsen.book.repository.BookRegistryRepository;
import com.modsen.book.repository.BookRepository;
import com.modsen.book.service.BookRegistryService;
import com.modsen.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultBookRegistryService implements BookRegistryService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultBookRegistryService.class);
    private static final String BOOK_REGISTRY_NOT_FOUND_MESSAGE = "Book registry was not found. ID: %s";

    private final BookRegistryRepository bookRegistryRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Override
    public List<Book> findAllFreeBooks() {
        return bookRepository.findAllByBookRegistriesIsNull();
    }

    @Override
    public List<BookRegistry> findAll() {
        return bookRegistryRepository.findAll();
    }

    @Override
    public BookRegistry save(BookRegistryUploadDTO registryDTO) {
        final UUID id = UUID.randomUUID();
        final Book book = bookService.findById(registryDTO.getBookId());

        LOG.info("Save new registry. ID: {}", id);
        return bookRegistryRepository.save(
                BookRegistry.builder()
                        .id(id)
                        .book(book)
                        .borrowedAt(LocalDate.now())
                        .returnAt(LocalDate.now().plusDays(3))
                        .build()
        );
    }

    @Override
    public void delete(UUID id) {
        bookRegistryRepository.deleteById(id);
        LOG.info("Delete registry. ID: {}", id);
    }

    @Override
    public BookRegistry findById(UUID id) {
        return bookRegistryRepository.findById(id)
                .orElseThrow(() ->
                        new BookRegistryNotFoundException(String.format(BOOK_REGISTRY_NOT_FOUND_MESSAGE, id))
                );
    }
}
