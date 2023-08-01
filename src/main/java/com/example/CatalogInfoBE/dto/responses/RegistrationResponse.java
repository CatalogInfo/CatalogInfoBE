package com.example.CatalogInfoBE.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter
@AllArgsConstructor
public class RegistrationResponse {

    public boolean usernameUses;

    public boolean emailUses;

}
