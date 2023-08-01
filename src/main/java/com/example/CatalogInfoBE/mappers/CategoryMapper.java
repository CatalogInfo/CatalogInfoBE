package com.example.CatalogInfoBE.mappers;

import com.example.CatalogInfoBE.dto.requests.CategoryRequest;
import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.models.table_entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper extends BaseDtoMapper<Category, CategoryRequest, CategoryResponse> {
  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

}
