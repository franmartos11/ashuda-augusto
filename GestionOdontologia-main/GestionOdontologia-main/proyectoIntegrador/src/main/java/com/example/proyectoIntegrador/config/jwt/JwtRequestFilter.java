package com.example.proyectoIntegrador.config.jwt;
import com.example.proyectoIntegrador.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestHeader = request.getHeader("Authorization");
        String username= null;
        String jwt = null;

        if(requestHeader != null &&  requestHeader.startsWith("Bearer")) {

            jwt = requestHeader.substring(7);
            username = jwtUtil.extractUserName(jwt);

        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var userDetails  = this.userService.loadUserByUsername(username);

            if(jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken  = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        filterChain.doFilter(request, response);
    }
}
