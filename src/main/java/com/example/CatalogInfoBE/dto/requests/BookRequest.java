package com.example.CatalogInfoBE.dto.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequest {
    Long id;
    String name;
    String style;
    String author;
    String text;
}
