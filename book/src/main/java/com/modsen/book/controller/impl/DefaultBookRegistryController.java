package com.modsen.book.controller.impl;

import com.modsen.book.controller.BookRegistryController;
import com.modsen.book.dto.BookRegistryUploadDTO;
import com.modsen.book.dto.BookRegistryViewingDTO;
import com.modsen.book.dto.BookViewingDTO;
import com.modsen.book.mapper.BookMapper;
import com.modsen.book.mapper.BookRegistryMapper;
import com.modsen.book.service.BookRegistryService;
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
public class DefaultBookRegistryController implements BookRegistryController {

    private final BookRegistryService bookRegistryService;
    private final BookMapper bookMapper;
    private final BookRegistryMapper bookRegistryMapper;

    @Override
    public ResponseEntity<List<BookViewingDTO>> findAllFreeBooks() {
        return ok(
                bookRegistryService.findAllFreeBooks()
                        .stream()
                        .map(bookMapper::toBookViewingDTO)
                        .toList()
        );
    }

    @Override
    public ResponseEntity<BookRegistryViewingDTO> findById(@PathVariable UUID id) {
        return ok(
                bookRegistryMapper.toBookRegistryViewingDTO(
                        bookRegistryService.findById(id)
                )
        );
    }

    @Override
    public ResponseEntity<List<BookRegistryViewingDTO>> findAll() {
        return ok(
                bookRegistryService.findAll()
                        .stream()
                        .map(bookRegistryMapper::toBookRegistryViewingDTO)
                        .toList()
        );
    }

    @Override
    public ResponseEntity<BookRegistryViewingDTO> save(@RequestBody BookRegistryUploadDTO book) {
        return ok(
                bookRegistryMapper.toBookRegistryViewingDTO(
                        bookRegistryService.save(book)
                )
        );
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookRegistryService.delete(id);
        return new ResponseEntity<>(OK);
    }

}
