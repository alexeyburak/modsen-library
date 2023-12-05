package com.modsen.book.service.impl;

import com.modsen.book.dto.BookUpdateDTO;
import com.modsen.book.dto.BookUploadDTO;
import com.modsen.book.exception.BookNotFoundException;
import com.modsen.book.model.Book;
import com.modsen.book.repository.BookRepository;
import com.modsen.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultBookService.class);
    private static final String BOOK_NOT_FOUND_MESSAGE = "Book was not found. Value: %s";

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE, id))
                );
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->
                        new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE, isbn))
                );

    }

    @Override
    public Book save(BookUploadDTO book) {
        final UUID id = UUID.randomUUID();

        LOG.info("Save new book. ID: {}", id);
        return bookRepository.save(
                Book.builder()
                        .id(id)
                        .title(book.getTitle())
                        .isbn(book.getIsbn())
                        .genre(book.getGenre())
                        .description(book.getDescription())
                        .author(book.getAuthor())
                        .build()
        );
    }

    @Override
    public Book update(UUID id, BookUpdateDTO bookDTO) {
        Book book = findById(id);

        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setGenre(bookDTO.getGenre());
        book.setIsbn(bookDTO.getIsbn());

        bookRepository.save(book);
        LOG.info("Update book. ID: {}", id);
        return book;
    }

    @Override
    public void delete(UUID id) {
        bookRepository.deleteById(id);
        LOG.info("Delete book. ID: {}", id);
    }

}
