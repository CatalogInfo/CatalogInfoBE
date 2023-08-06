package com.example.CatalogInfoBE.controllers.private_path;

import com.example.CatalogInfoBE.dto.requests.ArticleRequest;
import com.example.CatalogInfoBE.dto.requests.BookRequest;
import com.example.CatalogInfoBE.dto.responses.ArticleResponse;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.services.ArticleService;
import com.example.CatalogInfoBE.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping(value="/", produces = "application/json")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/category/{category_id}/article")
    public ResponseEntity<ArticleResponse> createArticle(@PathVariable("category_id") Long categoryId,
                                                         @RequestBody ArticleRequest articleRequest) {
        return new ResponseEntity<>(articleService.createArticle(articleRequest, categoryId), HttpStatus.CREATED);
    }

    @DeleteMapping("/category/{categoryId}/article/{articleId}")
    public ResponseEntity<String> deleteBook(@PathVariable("categoryId") Long categoryId,
                                             @PathVariable("articleId") Long articleId) {
        articleService.deleteArticle(articleId, categoryId);

        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

}
