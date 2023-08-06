package com.example.CatalogInfoBE.services;

import com.example.CatalogInfoBE.dto.requests.ArticleRequest;
import com.example.CatalogInfoBE.dto.requests.BookRequest;
import com.example.CatalogInfoBE.dto.responses.ArticleResponse;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.mappers.ArticleMapper;
import com.example.CatalogInfoBE.mappers.BookMapper;
import com.example.CatalogInfoBE.models.table_entities.Article;
import com.example.CatalogInfoBE.models.table_entities.Book;
import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.repos.ArticleRepository;
import com.example.CatalogInfoBE.repos.BookRepository;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ArticleResponse createArticle(ArticleRequest articleRequest, long categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        Article article = ArticleMapper.INSTANCE.toEntity(articleRequest);

        category.addArticle(article);
        article.setCategory(category);

        articleRepository.save(article);
        categoryRepository.save(category);

        return ArticleMapper.INSTANCE.toDto(article);
    }

    public void deleteArticle(long id, long categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        Article article = articleRepository.getReferenceById(id);

        category.removeArticle(article);
        articleRepository.delete(article);
        categoryRepository.save(category);
    }
}
