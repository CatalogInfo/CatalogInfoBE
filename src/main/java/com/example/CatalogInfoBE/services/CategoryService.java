package com.example.CatalogInfoBE.services;

import com.example.CatalogInfoBE.dto.requests.CategoryRequest;
import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.mappers.CategoryMapper;
import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.models.table_entities.User;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import com.example.CatalogInfoBE.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepo userRepo;

    public CategoryResponse createCategory(CategoryRequest categoryRequest, User user) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryRequest);

        category.setUser(user);
        user.addCategory(category);

        categoryRepository.save(category);
        userRepo.save(user);

        return CategoryMapper.INSTANCE.toDto(category);
    }
}
