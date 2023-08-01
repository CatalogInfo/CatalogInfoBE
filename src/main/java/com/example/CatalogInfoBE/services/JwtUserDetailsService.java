package com.example.CatalogInfoBE.services;

import com.example.CatalogInfoBE.models.table_entities.User;
import com.example.CatalogInfoBE.repos.UserRepo;
import com.example.CatalogInfoBE.security.JwtTokenUtil;
import com.example.CatalogInfoBE.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

	@org.springframework.beans.factory.annotation.Autowired
    UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User not found by username " + username);

        return new MyUserDetails(user);
    }

    public User getUserFromHeaders(HttpHeaders headers) {

        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);

        assert token != null;

        String final_token = token.substring(7);

        String username = jwtTokenUtil.getUsernameFromToken(final_token);

        User user = userRepo.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User not found by username " + username);

        return user;
    }
}