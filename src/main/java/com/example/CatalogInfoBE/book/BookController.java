package com.example.CatalogInfoBE.book;

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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category/{category_id}/book")
    public ResponseEntity<List<BookResponse>> getAllBooksByCategoryId(@PathVariable("category_id") Long categoryId) {
        ArrayList<Book> books = new ArrayList<>(bookRepository.findByCategoryId(categoryId));
        ArrayList<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : books) {

            BookResponse bookResponse = new BookResponse();

            bookResponse.setText(book.getText());
            bookResponse.setAuthor(book.getAuthor());
            bookResponse.setName(book.getName());
            bookResponse.setStyle(book.getStyle());

            bookResponses.add(bookResponse);
        }
            return new ResponseEntity<>(bookResponses, HttpStatus.OK);
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
    public ResponseEntity<String> updateBook(@PathVariable("books_id") Long bookId, @RequestBody Book bookRequest) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " not found"));

        book.setText(bookRequest.getText());
        book.setAuthor(bookRequest.getAuthor());
        book.setName(bookRequest.getName());
        book.setStyle(bookRequest.getStyle());
        bookRepository.save(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/book/{books_id}")
    public ResponseEntity<String> deleteBook(@PathVariable("books_id") long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);

    }

    @DeleteMapping("/category/{category_id}/book")
    public ResponseEntity<String> deleteAllBooksOfCategory(@PathVariable("category_id") long id) {
        categoryRepository.deleteById(id);
            return new ResponseEntity<>("Category has been deleted successfully",HttpStatus.OK);

        }
    }
