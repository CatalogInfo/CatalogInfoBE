package com.example.CatalogInfoBE.dto.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleResponse {
    private Long id;
    private Long category;
    private String link;
    private String title;
}
