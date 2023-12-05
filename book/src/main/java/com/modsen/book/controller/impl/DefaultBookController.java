package com.modsen.book.controller.impl;

import com.modsen.book.controller.BookController;
import com.modsen.book.dto.BookUpdateDTO;
import com.modsen.book.dto.BookUploadDTO;
import com.modsen.book.dto.BookViewingDTO;
import com.modsen.book.mapper.BookMapper;
import com.modsen.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class DefaultBookController implements BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Override
    public ResponseEntity<List<BookViewingDTO>> findAll() {
        return ok(
                bookService.findAll()
                        .stream()
                        .map(bookMapper::toBookViewingDTO)
                        .toList()
        );
    }

    @Override
    public ResponseEntity<BookViewingDTO> findById(@PathVariable UUID id) {
        return ok(
                bookMapper.toBookViewingDTO(
                        bookService.findById(id)
                )
        );
    }

    @Override
    public ResponseEntity<BookViewingDTO> findByIsbn(@PathVariable String isbn) {
        return ok(
                bookMapper.toBookViewingDTO(
                        bookService.findByIsbn(isbn)
                )
        );
    }

    @Override
    public ResponseEntity<BookViewingDTO> save(@RequestBody BookUploadDTO book) {
        return ok(
                bookMapper.toBookViewingDTO(
                        bookService.save(book)
                )
        );
    }

    @Override
    public ResponseEntity<BookViewingDTO> update(@PathVariable UUID id, @RequestBody BookUpdateDTO book) {
        return ok(
                bookMapper.toBookViewingDTO(
                        bookService.update(id, book)
                )
        );
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.delete(id);
        return new ResponseEntity<>(OK);
    }

}
