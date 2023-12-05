package com.modsen.book.controller;

import com.modsen.book.dto.BookRegistryUploadDTO;
import com.modsen.book.dto.BookRegistryViewingDTO;
import com.modsen.book.dto.BookViewingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Tag(name = "Libraries")
@RequestMapping("/libraries")
public interface BookRegistryController {

    @Operation(summary = "Get list of free books")
    @GetMapping("/free")
    ResponseEntity<List<BookViewingDTO>> findAllFreeBooks();

    @Operation(summary = "Get book registry by ID")
    @GetMapping("/{id}")
    ResponseEntity<BookRegistryViewingDTO> findById(@PathVariable UUID id);

    @Operation(summary = "Get list of book registries")
    @GetMapping
    ResponseEntity<List<BookRegistryViewingDTO>> findAll();

    @Operation(summary = "Save book registry")
    @PostMapping
    ResponseEntity<BookRegistryViewingDTO> save(@RequestBody BookRegistryUploadDTO book);

    @Operation(summary = "Delete book registry by ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);

}
