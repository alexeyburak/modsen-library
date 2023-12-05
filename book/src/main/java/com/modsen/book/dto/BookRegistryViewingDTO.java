package com.modsen.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRegistryViewingDTO {
    private UUID id;
    private BookViewingDTO book;
    private LocalDate borrowedAt;
    private LocalDate returnAt;
}
