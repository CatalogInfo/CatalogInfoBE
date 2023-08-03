package com.example.CatalogInfoBE.controllers.private_path;

import com.example.CatalogInfoBE.dto.requests.BookRequest;
import com.example.CatalogInfoBE.models.table_entities.Book;
import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.repos.BookRepository;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping(value="/", produces = "application/json")
public class BookController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookService bookService;

//    @GetMapping("/book/{book_id}")
//    public ResponseEntity<BookResponse> getBookById(@PathVariable("book_id") Long id) {
//        Book book = bookRepository.getReferenceById(id);
//
//        BookResponse bookResponse = new BookResponse(book.getId(), book.getName(), book.getStyle(), book.getAuthor(), book.getText(), book.getCategory().getId());
//
//        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
//    }

    @PostMapping("/category/{category_id}/book")
    public ResponseEntity<BookResponse> createBook(@PathVariable("category_id") Long categoryId,
                                             @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.createBook(bookRequest, categoryId), HttpStatus.CREATED);
    }

//    @PutMapping("/book/{books_id}")
//    public ResponseEntity<String> updateBook(@PathVariable("books_id") Long bookId, @RequestBody Book bookRequest) {
//        Book book = bookRepository.getReferenceById(bookId);
//
//        book.setText(bookRequest.getText());
//        book.setAuthor(bookRequest.getAuthor());
//        book.setName(bookRequest.getName());
//        book.setStyle(bookRequest.getStyle());
//        bookRepository.save(book);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/book/{books_id}")
//    public ResponseEntity<String> deleteBook(@PathVariable("books_id") long id) {
//        bookRepository.deleteById(id);
//        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
//
//    }
//
//    @DeleteMapping("/category/{category_id}/book")
//    public ResponseEntity<String> deleteAllBooksOfCategory(@PathVariable("category_id") long id) {
//        categoryRepository.deleteById(id);
//            return new ResponseEntity<>("Category has been deleted successfully",HttpStatus.OK);
//
//        }
    }
