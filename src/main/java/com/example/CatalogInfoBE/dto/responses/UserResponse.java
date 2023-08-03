package com.example.CatalogInfoBE.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserResponse {
  private Long id;
  private String username;
  private String email;

}
