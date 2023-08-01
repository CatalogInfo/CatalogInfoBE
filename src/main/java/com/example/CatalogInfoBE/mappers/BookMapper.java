package com.example.CatalogInfoBE.mappers;

import com.example.CatalogInfoBE.dto.requests.BookRequest;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.models.table_entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { ModelMapper.class, CategoryMapper.class })
public interface BookMapper extends BaseDtoMapper<Book, BookRequest, BookResponse> {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
}
