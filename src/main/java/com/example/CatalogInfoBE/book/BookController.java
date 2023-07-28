package com.example.CatalogInfoBE.book;

import com.example.CatalogInfoBE.book.Book;
import com.example.CatalogInfoBE.book.BookRepository;
import com.example.CatalogInfoBE.category.Category;
import com.example.CatalogInfoBE.category.CategoryRepository;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category/{categoryId}/book")
    public ResponseEntity<List<Book>> getAllBooksByCategoryId(@PathVariable("category_id") Long category_id) {
        if (!categoryRepository.existsById(category_id)) {
            throw new ResourceNotFoundException("Not found Category with id : " + category_id);
        }

        List<Book> books = bookRepository.findByCategoryId(category_id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{book_id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("book_id") Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Book with id : " + id));

        BookResponse bookResponse = new BookResponse(book.getId(), book.getName(), book.getStyle(), book.getAuthor(), book.getText(), book.getCategory().getId());

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping("/category/{category_id}/book")
    public ResponseEntity<String> createBook(@PathVariable("category_id") Long categoryId,
                                           @RequestBody Book bookRequest) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId " + categoryId + " not found"));

        Book book = new Book();
        book.setCategory(category);
        book.setText(bookRequest.getText());
        book.setAuthor(bookRequest.getAuthor());
        book.setName(bookRequest.getName());
        book.setStyle(bookRequest.getStyle());

        category.addBook(book);

        bookRepository.save(book);
        categoryRepository.save(category);

        return new ResponseEntity<>("created", HttpStatus.CREATED);

    }

    @PutMapping("/book/{books_id}")
    public ResponseEntity<Book> updateBook(@PathVariable("books_id") long id, @RequestBody Book bookRequest) {
        Book books = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookId " + id + " not found"));

        books.setText(bookRequest.getText());

        return new ResponseEntity<>(bookRepository.save(books), HttpStatus.OK);
    }

    @DeleteMapping("/book/{books_id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("books_id") long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/category/{category_id}/book")
    public ResponseEntity<List<Book>> deleteAllBooksOfCategory(@PathVariable("category_id") Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Not found Category with id : " + categoryId);
        }

        bookRepository.deleteByCategoryId(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
