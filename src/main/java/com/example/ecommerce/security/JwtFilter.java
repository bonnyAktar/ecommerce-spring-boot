package com.example.ecommerce.security;

import com.example.ecommerce.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if (authHeader !=null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            String email= jwtService.extractemail(token);

            UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(email,null,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request,response);
    }
}
