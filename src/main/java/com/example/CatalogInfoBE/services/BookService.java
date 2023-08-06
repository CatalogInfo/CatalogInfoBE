package com.example.CatalogInfoBE.services;

import com.example.CatalogInfoBE.dto.requests.BookRequest;
import com.example.CatalogInfoBE.dto.responses.BookResponse;
import com.example.CatalogInfoBE.mappers.BookMapper;
import com.example.CatalogInfoBE.models.table_entities.Book;
import com.example.CatalogInfoBE.models.table_entities.Category;
import com.example.CatalogInfoBE.repos.BookRepository;
import com.example.CatalogInfoBE.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public BookResponse createBook(BookRequest bookRequest, long categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        Book book = BookMapper.INSTANCE.toEntity(bookRequest);

        category.addBook(book);
        book.setCategory(category);

        bookRepository.save(book);
        categoryRepository.save(category);

        return BookMapper.INSTANCE.toDto(book);
    }

    public void deleteBook(long id, long categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        Book book = bookRepository.getReferenceById(id);

        category.removeBook(book);
        bookRepository.delete(book);
        categoryRepository.save(category);
    }

}
