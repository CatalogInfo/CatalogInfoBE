package com.example.CatalogInfoBE.dto.requests;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequest {

    Long id;

    String name;

}
