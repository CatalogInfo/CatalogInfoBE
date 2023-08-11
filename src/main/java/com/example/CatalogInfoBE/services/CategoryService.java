package com.example.CatalogInfoBE.services;

import com.example.CatalogInfoBE.dto.requests.CategoryRequest;
import com.example.CatalogInfoBE.dto.responses.ArticleResponse;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.dto.responses.VideoResponse;
import com.example.CatalogInfoBE.mappers.ArticleMapper;
import com.example.CatalogInfoBE.mappers.BookMapper;
import com.example.CatalogInfoBE.mappers.CategoryMapper;
import com.example.CatalogInfoBE.mappers.VideoMapper;
import com.example.CatalogInfoBE.models.table_entities.*;
import com.example.CatalogInfoBE.repos.BookRepository;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import com.example.CatalogInfoBE.repos.UserRepo;
import com.example.CatalogInfoBE.repos.VideoRepository;
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
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private VideoRepository videoRepository;
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

    public List<ArticleResponse> getArticles(long categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        ArrayList<Article> articles = new ArrayList<>(category.getArticles());

        return ArticleMapper.INSTANCE.toDtos(articles);
    }

    public List<CategoryResponse> getChildren(long categoryId) {

        Category category = categoryRepository.getReferenceById(categoryId);

        List<Category> categories = categoryRepository.findByParent(category);

        return CategoryMapper.INSTANCE.toDtos(categories);
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest, User user) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryRequest);

        category.setUser(user);
        user.addCategory(category);

        categoryRepository.save(category);
        userRepo.save(user);

        return CategoryMapper.INSTANCE.toDto(category);
    }

    public CategoryResponse createChild(CategoryRequest categoryRequest, long categoryId, User user) {
        Category category = categoryRepository.getReferenceById(categoryId);
        Category child = CategoryMapper.INSTANCE.toEntity(categoryRequest);

        child.setParent(category);
        category.addChildCategory(child);
        child.setUser(user);

        categoryRepository.save(child);
        categoryRepository.save(category);
        userRepo.save(user);


        return CategoryMapper.INSTANCE.toDto(child);
    }

    public String deleteCategory(long categoryId, User user) {
        Category category = categoryRepository.getReferenceById(categoryId);

        while (!category.getChildCategories().isEmpty()) {
            deleteCategoryRecursive(category, user);
        }

        deleteCategory(category, user);

        return "deleted";
    }

    private void removeParent(Category category) {
        if (category.getParent() != null) {
            Category parent = category.getParent();
            category.setParent(null);
            categoryRepository.save(category);
            parent.removeChildCategory(category);
            categoryRepository.save(parent);
        }
    }

    private void deleteCategory(Category category, User user) {
        List<Book> books = category.getBooks();
        List<Video> videos = category.getVideos();

        for(Book book : books) {
            bookRepository.delete(book);
        }
        for(Video video : videos) {
            videoRepository.delete(video);
        }
        removeParent(category);
        user.removeCategory(category);
        userRepo.save(user);
        categoryRepository.delete(category);
    }

    public void deleteCategoryRecursive(Category category, User user) {

        if(!category.getChildCategories().isEmpty()) {
            deleteCategoryRecursive(category.getChildCategories().get(0), user);
        } else {
            deleteCategory(category, user);
        }
    }
}
