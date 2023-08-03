package com.example.CatalogInfoBE.controllers.private_path;

import com.example.CatalogInfoBE.dto.requests.CategoryRequest;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.models.table_entities.Book;
import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.models.table_entities.User;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.services.CategoryService;
import com.example.CatalogInfoBE.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping(value="/category", produces = "application/json")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @GetMapping("/{categoryId}/books")
    public ResponseEntity<List<BookResponse>> getBooks(@RequestHeader HttpHeaders headers, @PathVariable("categoryId") long categoryId) {
        return new ResponseEntity<>(categoryService.getBooks(categoryId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest, @RequestHeader HttpHeaders headers) {
        User user = jwtUserDetailsService.getUserFromHeaders(headers);

        return new ResponseEntity<>(categoryService.createCategory(categoryRequest, user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("category_id") long id, @RequestBody Category category) {
        Category _category = categoryRepository.getReferenceById(id);

        _category.setName(category.getName());

        return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("category_id") long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllCategories() {
        categoryRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}