package com.example.CatalogInfoBE.dto.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class VideoRequest {
    private String id;
    private String link;
    private String title;
    private String channelTitle;
}
