package com.example.CatalogInfoBE.repos;

import com.example.CatalogInfoBE.models.table_entities.Article;
import com.example.CatalogInfoBE.models.table_entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}

