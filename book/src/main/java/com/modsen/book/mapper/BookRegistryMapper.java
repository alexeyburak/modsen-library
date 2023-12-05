package com.modsen.book.mapper;

import com.modsen.book.config.MapperConfig;
import com.modsen.book.dto.BookRegistryViewingDTO;
import com.modsen.book.model.BookRegistry;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookRegistryMapper {
    BookRegistryViewingDTO toBookRegistryViewingDTO(BookRegistry bookRegistry);
}
