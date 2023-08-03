package com.example.CatalogInfoBE.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final String tip;

    public JwtResponse(String token, String tip) {
        this.token = token;
        this.tip = tip;
    }
}
