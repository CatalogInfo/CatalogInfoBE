package com.example.CatalogInfoBE.dto.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class UserRequest {

  private String username;
  
  private String email;

  private String password;
}
