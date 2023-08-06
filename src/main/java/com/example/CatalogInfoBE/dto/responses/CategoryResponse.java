package com.example.CatalogInfoBE.dto.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private Long id;
    private List<Long> books;
    private List<String> videos;
    private List<Long> articles;
    private String name;
}
