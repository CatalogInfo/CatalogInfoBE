package com.example.CatalogInfoBE.models.table_entities;

import com.example.CatalogInfoBE.models.interfaces.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Model {

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", categories=" + categories +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories;

    private String username;

    private String password;

    private String email;


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }
}
