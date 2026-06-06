package com.trimzo.config;

import com.trimzo.service.UserDetailsServiceImpl;
import com.trimzo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Header se token nikalo
        String authHeader = request.getHeader("Authorization");

        // 2. Token hai? "Bearer " se shuru hota hai?
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. "Bearer " hata ke sirf token lo
        String token = authHeader.substring(7);

        // 4. Token valid hai?
        if (!jwtUtil.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 5. Token se email nikalo
        String email = jwtUtil.extractEmail(token);

        // 6. User load karo
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(email);

        // 7. Security context mein set karo
        // "Yeh user authenticated hai!"
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities());

        authToken.setDetails(
                new WebAuthenticationDetailsSource()
                        .buildDetails(request));

        SecurityContextHolder.getContext()
                .setAuthentication(authToken);

        // 8. Aage jaane do
        filterChain.doFilter(request, response);
    }
}