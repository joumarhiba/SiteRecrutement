package com.emploi.security;

import com.emploi.model.Admin;
import com.emploi.model.Company;
import com.emploi.service.AdminService;
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
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtHandler jwtHandler;
    private final CompanyService companyService;
    private final UserDetailsService userDetailsService;


    // old code v1
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
           // Company companyDetails = null;
            if ("ADMIN".equals(userRole)) {
                System.out.println("--------> inside jwt filter where the role = "+userRole);
                userDetails = userDetailsService.loadUserByUsername(userEmail);
            } else if ("COMPANY".equals(userRole)) {
                System.out.println("--------> inside jwt filter where the role = "+userRole);
                userDetails = companyService.loadUserByEmail(userEmail);
            }


            if (userDetails != null) {
                isTokenValid = jwtHandler.isValidateToken(jwt, userDetails);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else if (userDetails != null) {
                isTokenValid = jwtHandler.validateTokenCompany(jwt, userDetails);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else {
                System.out.println("something Wrong in JWTAuthFilter");
            }
            filterChain.doFilter(request, response);
        }
    }
}