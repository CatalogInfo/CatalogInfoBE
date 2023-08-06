package com.example.CatalogInfoBE.controllers.private_path;

import com.example.CatalogInfoBE.dto.requests.CategoryRequest;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.dto.responses.VideoResponse;
import com.example.CatalogInfoBE.models.table_entities.User;
import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.services.CategoryService;
import com.example.CatalogInfoBE.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping(value="/category", produces = "application/json")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @GetMapping("/{categoryId}/books")
    public ResponseEntity<List<BookResponse>> getBooks(@RequestHeader HttpHeaders headers, @PathVariable("categoryId") long categoryId) {
        return new ResponseEntity<>(categoryService.getBooks(categoryId), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/videos")
    public ResponseEntity<List<VideoResponse>> getVideos(@RequestHeader HttpHeaders headers, @PathVariable("categoryId") long categoryId) {
        return new ResponseEntity<>(categoryService.getVideos(categoryId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest, @RequestHeader HttpHeaders headers) {
        User user = jwtUserDetailsService.getUserFromHeaders(headers);

        return new ResponseEntity<>(categoryService.createCategory(categoryRequest, user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") long categoryId, @RequestHeader HttpHeaders headers) {
        User user = jwtUserDetailsService.getUserFromHeaders(headers);

        return new ResponseEntity<>(categoryService.deleteCategory(categoryId, user), HttpStatus.CREATED);
    }

}