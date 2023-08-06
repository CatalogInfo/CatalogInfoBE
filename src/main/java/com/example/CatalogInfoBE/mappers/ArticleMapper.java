package com.example.CatalogInfoBE.mappers;

import com.example.CatalogInfoBE.dto.requests.ArticleRequest;
import com.example.CatalogInfoBE.dto.requests.CategoryRequest;
import com.example.CatalogInfoBE.dto.responses.ArticleResponse;
import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.models.table_entities.Article;
import com.example.CatalogInfoBE.models.table_entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { ModelMapper.class, CategoryMapper.class })
public interface ArticleMapper extends BaseDtoMapper<Article, ArticleRequest, ArticleResponse> {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
}
