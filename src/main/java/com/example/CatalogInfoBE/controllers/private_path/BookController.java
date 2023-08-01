package com.example.CatalogInfoBE.controllers.private_path;

import com.example.CatalogInfoBE.book.Book;
import com.example.CatalogInfoBE.category.Category;
import com.example.CatalogInfoBE.repos.BookRepository;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
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
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category/{category_id}/book")
    public ResponseEntity<List<BookResponse>> getAllBooksByCategoryId(@PathVariable("category_id") Long categoryId) {
        ArrayList<Book> books = new ArrayList<>(bookRepository.findByCategoryId(categoryId));
        ArrayList<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : books) {

            BookResponse bookResponse = new BookResponse();

            bookResponse.setId(book.getId());
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
        Book book = bookRepository.getReferenceById(id);

        BookResponse bookResponse = new BookResponse(book.getId(), book.getName(), book.getStyle(), book.getAuthor(), book.getText(), book.getCategory().getId());

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping("/category/{category_id}/book")
    public ResponseEntity<BookResponse> createBook(@PathVariable("category_id") Long categoryId,
                                             @RequestBody Book bookRequest) {
        Category category = categoryRepository.getReferenceById(categoryId);

        System.out.println(String.valueOf(bookRequest.getAuthor()));
        Book book = new Book();
        book.setCategory(category);
        book.setText(bookRequest.getText());
        book.setAuthor(bookRequest.getAuthor());
        book.setName(bookRequest.getName());
        book.setStyle(bookRequest.getStyle());

        category.addBook(book);

        bookRepository.save(book);
        categoryRepository.save(category);

        BookResponse bookResponse = new BookResponse(book.getId(), book.getName(), book.getStyle(), book.getAuthor(), book.getText(), book.getCategory().getId());


        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);

    }

    @PutMapping("/book/{books_id}")
    public ResponseEntity<String> updateBook(@PathVariable("books_id") Long bookId, @RequestBody Book bookRequest) {
        Book book = bookRepository.getReferenceById(bookId);

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
