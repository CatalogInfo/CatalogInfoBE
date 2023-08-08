package com.example.CatalogInfoBE.dto.responses;

import com.example.CatalogInfoBE.models.table_entities.Category;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private Long id;
    private Long parent;
    private List<Long> books;
    private List<String> videos;
    private List<Long> articles;
    private List<CategoryResponse> children;
    private String name;
    private boolean hasChildren;
}
