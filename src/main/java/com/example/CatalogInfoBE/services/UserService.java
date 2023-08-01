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

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {

    public List<CategoryResponse> getCategories(User user) {
        return CategoryMapper.INSTANCE.toDtos(user.getCategories());
    }

}
