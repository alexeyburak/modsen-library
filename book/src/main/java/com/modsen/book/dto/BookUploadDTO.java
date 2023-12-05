package com.modsen.book.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookUploadDTO {

    @Size(min = 2, max = 40, message = "INVALID_TITLE_SIZE")
    private String title;

    @Size(min = 2, max = 40, message = "INVALID_ISBN_SIZE")
    private String isbn;

    @Size(min = 2, max = 40, message = "INVALID_GENRE_SIZE")
    private String genre;

    @Size(min = 2, max = 40, message = "INVALID_DESCRIPTION_SIZE")
    private String description;

    @Size(min = 2, max = 40, message = "INVALID_AUTHOR_SIZE")
    private String author;
}
