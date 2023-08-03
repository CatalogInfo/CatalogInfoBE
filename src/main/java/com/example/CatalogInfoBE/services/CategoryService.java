package com.example.CatalogInfoBE.services;

import com.example.CatalogInfoBE.dto.requests.CategoryRequest;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.dto.responses.VideoResponse;
import com.example.CatalogInfoBE.mappers.BookMapper;
import com.example.CatalogInfoBE.mappers.CategoryMapper;
import com.example.CatalogInfoBE.mappers.VideoMapper;
import com.example.CatalogInfoBE.models.table_entities.Book;
import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.models.table_entities.User;
import com.example.CatalogInfoBE.models.table_entities.Video;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import com.example.CatalogInfoBE.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepo userRepo;


    public List<BookResponse> getBooks(long categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        ArrayList<Book> books = new ArrayList<>(category.getBooks());

        return BookMapper.INSTANCE.toDtos(books);
    }

    public List<VideoResponse> getVideos(long categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        ArrayList<Video> videos = new ArrayList<>(category.getVideos());

        return VideoMapper.INSTANCE.toDtos(videos);
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest, User user) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryRequest);

        category.setUser(user);
        user.addCategory(category);

        categoryRepository.save(category);
        userRepo.save(user);

        return CategoryMapper.INSTANCE.toDto(category);
    }
}
