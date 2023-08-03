package com.example.CatalogInfoBE.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class RegistrationResponse {
    private boolean usernameUses;
    private boolean emailUses;
}
