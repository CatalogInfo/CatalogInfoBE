package com.example.CatalogInfoBE.models.table_entities;

import com.example.CatalogInfoBE.models.interfaces.Model;
import com.example.CatalogInfoBE.models.table_entities.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Video> videos;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Article> articles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Category> childCategories;

    public void addBook(Book book) {
        books.add(book);
    }
    public void addVideo(Video video) {
        videos.add(video);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void removeVideo(Video video) {
        videos.remove(video);
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }

    public void addChildCategory(Category category) {
        childCategories.add(category);
    }

    public void removeChildCategory(Category category) {
        childCategories.remove(category);
    }

}
