package com.example.CatalogInfoBE.dto.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class BookResponse {
    private Long id;
    private String name;
    private String style;
    private String author;
    private String text;
    private Long category;
}

