package com.example.CatalogInfoBE.dto.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleRequest {
    private String link;
    private String title;
}
