package com.example.CatalogInfoBE.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MakeResponse {
    public static <T> ResponseEntity<T> makeOkResponse(T body) {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> makeConflictResponse(T body) {
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    public static <T> ResponseEntity<T> makeNotFoundResponse(T body) {
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<T> makeUnauthorizedResponse(T body) {
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    public static <T> ResponseEntity<T> makeBadRequestResponse(T body) {
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
