package com.cdac.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService userDetailsService;
    @Autowired
    private JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // extract Authorization header from request
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader);

        String username = null;
        String token = null;

        // check for correct format
        if (request != null && authorizationHeader.startsWith("Bearer ")) {
            // extract token form authorization header
            token = authorizationHeader.substring(7);

            try {
                // extract username from token
                username = this.jwtUtils.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get jwt token.");
            } catch (ExpiredJwtException e) {
                System.out.println("Token has expired.");
            } catch (MalformedJwtException e) {
                System.out.println("Invalid jwt.");
            }

        } else {
            System.out.println("Token format invalid, not starting with Bearer .");
        }

        // Now Validate user and authenticate
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtils.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Invalid token.");
            }

        } else {
            System.out.println("Username is null or Context is not null.");
        }

        filterChain.doFilter(request, response);

    }
}
