package com.modsen.book.mapper;

import com.modsen.book.config.MapperConfig;
import com.modsen.book.dto.BookViewingDTO;
import com.modsen.book.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookViewingDTO toBookViewingDTO(Book book);
}
