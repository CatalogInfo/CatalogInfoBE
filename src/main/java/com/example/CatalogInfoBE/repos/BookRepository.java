package com.example.CatalogInfoBE.repos;

import com.example.CatalogInfoBE.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByNameContaining(String name);
    List<Book> findAll();
    Book findById(String id);
    List<Book> findByAuthor(String author);
    void deleteAll();
    List<Book> findByCategoryId(Long category_id);
    void deleteByCategoryId(Long category_id);
}
