package com.example.CatalogInfoBE.services;


import com.example.CatalogInfoBE.dto.requests.JwtRequest;
import com.example.CatalogInfoBE.dto.requests.UserRequest;
import com.example.CatalogInfoBE.dto.responses.RegistrationResponse;
import com.example.CatalogInfoBE.models.table_entities.User;
import com.example.CatalogInfoBE.repos.UserRepo;
import com.example.CatalogInfoBE.security.JwtTokenUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;
    @Lazy
    @Autowired
    private PasswordEncoder bcryptEncoderStart;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    public String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception{

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        return jwtTokenUtil.generateToken(userDetails);
    }

    public boolean validateToken(String validateToken) throws NullPointerException{
        JSONObject json = new JSONObject(validateToken);
        String token = String.valueOf(json.get("token"));
        return jwtTokenUtil.isTokenExpired(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public RegistrationResponse save(UserRequest user) {

        boolean usernameUses = userRepo.existsByUsername(user.getUsername());
        boolean emailUses = userRepo.existsByEmail(user.getEmail());

        RegistrationResponse response = new RegistrationResponse(usernameUses, emailUses);

        if (usernameUses || emailUses)
            return response;

        userRepo.save(new User(
             user.getUsername(),
             bcryptEncoderStart.encode(user.getPassword()),
             user.getEmail()
             )
        );

        return response;
    }
}
