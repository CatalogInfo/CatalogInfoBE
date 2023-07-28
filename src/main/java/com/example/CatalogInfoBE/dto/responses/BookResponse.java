package com.example.CatalogInfoBE.dto.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookResponse {
    Long id;
    String name;
    String style;
    String author;
    String text;
    Long category;
}

