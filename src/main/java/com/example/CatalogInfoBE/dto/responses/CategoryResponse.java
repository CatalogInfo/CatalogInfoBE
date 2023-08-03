package com.example.CatalogInfoBE.dto.responses;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    Long id;
    List<Long> books;
    String name;
}
