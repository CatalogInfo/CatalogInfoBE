package com.example.CatalogInfoBE.repos;

import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.models.table_entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContainingAndUser(String name, User user);
    List<Category> findByParent(Category category);
}
