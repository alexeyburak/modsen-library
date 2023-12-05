package com.modsen.book.controller;

import com.modsen.book.dto.BookUpdateDTO;
import com.modsen.book.dto.BookUploadDTO;
import com.modsen.book.dto.BookViewingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Tag(name = "Books")
@RequestMapping("/books")
public interface BookController {

    @Operation(summary = "Get list of books")
    @GetMapping
    ResponseEntity<List<BookViewingDTO>> findAll();

    @Operation(summary = "Get book by ID")
    @GetMapping("/{id}")
    ResponseEntity<BookViewingDTO> findById(@PathVariable UUID id);

    @Operation(summary = "Get book by ISBN")
    @GetMapping("/isbn/{isbn}")
    ResponseEntity<BookViewingDTO> findByIsbn(@PathVariable String isbn);

    @Operation(summary = "Save book")
    @PostMapping
    ResponseEntity<BookViewingDTO> save(@RequestBody BookUploadDTO book);

    @Operation(summary = "Update book by ID")
    @PutMapping("/{id}")
    ResponseEntity<BookViewingDTO> update(@PathVariable UUID id, @RequestBody BookUpdateDTO book);

    @Operation(summary = "Delete book by ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);

}
