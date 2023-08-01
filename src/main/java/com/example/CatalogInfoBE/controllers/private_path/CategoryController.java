package com.example.CatalogInfoBE.controllers.private_path;

import com.example.CatalogInfoBE.dto.requests.CategoryRequest;
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
@RequestMapping(value="/", produces = "application/json")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

//    @GetMapping("/category")
//    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
//        List<Category> categories = categoryRepository.findAll();
//
//        List<CategoryResponse> response = new ArrayList<>();
//
//        for(Category category : categories) {
//            CategoryResponse categoryResponse = new CategoryResponse();
//            categoryResponse.setId(category.getId());
//            categoryResponse.setName(category.getName());
//
//            for(Book book : category.getBooks()){
//                categoryResponse.addBook(book.getId());
//            }
//            response.add(categoryResponse);
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @GetMapping("/category/{id}")
//    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("category_id") long id) {
//        Category category = categoryRepository.getReferenceById(id);
//
//        CategoryResponse response = new CategoryResponse();
//
//        CategoryResponse categoryResponse = new CategoryResponse();
//        categoryResponse.setId(category.getId());
//        categoryResponse.setName(category.getName());
//
//        for(Book book : category.getBooks()){
//            categoryResponse.addBook(book.getId());
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest, @RequestHeader HttpHeaders headers) {
        User user = jwtUserDetailsService.getUserFromHeaders(headers);

        return new ResponseEntity<>(categoryService.createCategory(categoryRequest, user), HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("category_id") long id, @RequestBody Category category) {
        Category _category = categoryRepository.getReferenceById(id);

        _category.setName(category.getName());

        return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("category_id") long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/category")
    public ResponseEntity<HttpStatus> deleteAllCategories() {
        categoryRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}