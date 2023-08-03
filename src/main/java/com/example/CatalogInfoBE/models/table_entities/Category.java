package com.example.CatalogInfoBE.models.table_entities;

import com.example.CatalogInfoBE.models.interfaces.Model;
import com.example.CatalogInfoBE.models.table_entities.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> books;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Video> videos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    public void addBook(Book book) {
        books.add(book);
    }
    public void addVideo(Video video) {
        videos.add(video);
    }

}
