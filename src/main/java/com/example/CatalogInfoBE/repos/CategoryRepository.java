package com.example.CatalogInfoBE.repos;

import com.example.CatalogInfoBE.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContaining(String name);
}
