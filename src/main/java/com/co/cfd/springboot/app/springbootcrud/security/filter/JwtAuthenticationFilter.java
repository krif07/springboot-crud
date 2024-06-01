package com.co.cfd.springboot.app.springbootcrud.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.co.cfd.springboot.app.springbootcrud.entities.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.co.cfd.springboot.app.springbootcrud.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        User user = null;
        String username = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(),User.class);
            username = user.getUsername();
            password = user.getPassword();
        } catch(StreamReadException e) {
            e.printStackTrace();
        } catch(DatabindException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken 
            = new UsernamePasswordAuthenticationToken(username, password);
        
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        org.springframework.security.core.userdetails.User user 
            = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities(); 
        
        Claims claims = Jwts.claims().build();
        claims.put("authorities", roles);

        String token = Jwts.builder()
                        .subject(username)
                        .claims(claims)
                        .expiration(new Date(System.currentTimeMillis() + 36000))
                        .issuedAt(new Date())
                        .signWith(SECRET_KEY)
                        .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN.concat(token));

        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", String.format("Hola %s has iniciado session con exito!", username));
        
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(HttpStatus.OK.value());
    }
    
}