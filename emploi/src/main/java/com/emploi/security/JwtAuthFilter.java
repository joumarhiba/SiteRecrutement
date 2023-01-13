package com.emploi.security;

import com.emploi.service.CompanyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
// every request should be authenticated
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtHandler jwtHandler;
    private final CompanyService companyService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
       HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // we call getHeader dans laquelle on va passe the header name 'Authorization' ==> il contient Bearer token

        final String authHeader = request.getHeader("AUTHORIZATION");
        final String userRole;
        final String userEmail;
        final String jwt;
        final boolean isTokenValid;

            //-> when we make a call we need to pass a JWT authentication token within a header & here we try to extract the header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

          //extraction of the token from header
        jwt = authHeader.substring(7);
        userEmail = jwtHandler.extractUsername(jwt);
        userRole = jwtHandler.extractRole(jwt);


        //check if the userEmail exist
        // SecurityContextHolder.getContext().getAuthentication() == null ====> the user is not connected yet
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            if ("ADMIN".equals(userRole)) {
                //in applicationConfig
                userDetails = userDetailsService.loadUserByUsername(userEmail);
            } else if ("COMPANY".equals(userRole)) {
                userDetails = companyService.loadUserByEmail(userEmail);
            }


            // validation du token
            if (userDetails != null) {
                isTokenValid = jwtHandler.isValidateToken(jwt, userDetails);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            else if (userDetails != null) {
                isTokenValid = jwtHandler.validateTokenCompany(jwt, userDetails);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            else {
                System.out.println("something Wrong in JWTAuthFilter");
            }
            filterChain.doFilter(request, response);
        }
    }
}