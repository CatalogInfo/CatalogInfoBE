package com.example.CatalogInfoBE.controllers.private_path;

import com.example.CatalogInfoBE.dto.responses.CategoryResponse;
import com.example.CatalogInfoBE.models.table_entities.Book;
import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.models.table_entities.User;
import com.example.CatalogInfoBE.services.JwtUserDetailsService;
import com.example.CatalogInfoBE.services.UserService;
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
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestHeader HttpHeaders headers) {
        User user = jwtUserDetailsService.getUserFromHeaders(headers);

        return new ResponseEntity<>(userService.getCategories(user), HttpStatus.OK);
    }

}
