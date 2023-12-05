package com.modsen.book.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRegistryUploadDTO {

    @NotNull(message = "NULL_BOOK_ID")
    private UUID bookId;
}
