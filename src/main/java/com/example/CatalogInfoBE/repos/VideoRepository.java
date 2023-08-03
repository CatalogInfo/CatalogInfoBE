package com.example.CatalogInfoBE.repos;

import com.example.CatalogInfoBE.models.table_entities.Book;
import com.example.CatalogInfoBE.models.table_entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAll();
    Video findById(String id);
    void deleteAll();
}
