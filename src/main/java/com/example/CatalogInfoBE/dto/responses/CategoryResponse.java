package com.example.CatalogInfoBE.dto.responses;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    Long id;
    String name;
    ArrayList<Long> books = new ArrayList<>();

    public void addBook(Long id) {
        books.add(id);
    }
}
