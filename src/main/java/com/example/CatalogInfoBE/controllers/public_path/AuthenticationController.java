package com.example.CatalogInfoBE.controllers.public_path;

import com.example.CatalogInfoBE.dto.requests.JwtRequest;
import com.example.CatalogInfoBE.dto.requests.UserRequest;
import com.example.CatalogInfoBE.dto.responses.JwtResponse;
import com.example.CatalogInfoBE.dto.responses.RegistrationResponse;
import com.example.CatalogInfoBE.services.AuthService;
import com.example.CatalogInfoBE.utils.MakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthenticationController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest user) {

        RegistrationResponse response = authService.save(user);

        if(response.isEmailUses() || response.isUsernameUses())
            return MakeResponse.makeConflictResponse(response);

        return MakeResponse.makeOkResponse(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtRequest authenticationRequest) {

        String token = "";
        try {
            token = authService.createAuthenticationToken(authenticationRequest);
        } catch (Exception e) {
            return MakeResponse.makeNotFoundResponse(e.getMessage());
        }
        return MakeResponse.makeOkResponse(new JwtResponse(token, "found"));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestBody String token) {

        try {
            if(authService.validateToken(token)) {
                return MakeResponse.makeUnauthorizedResponse("{\"expired\"" + ":" + true + "}");
            }
        } catch (Exception e) {
            return MakeResponse.makeBadRequestResponse(e.getMessage());
        }
        return MakeResponse.makeOkResponse("{\"expired\"" + ":" + false + "}");
    }
}
