package com.emploi.security;

import com.emploi.model.Admin;
import com.emploi.model.Company;
import com.emploi.model.UserRole;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHandler {

    Map<String, Object> claims = new HashMap<>();
    private String SECRET_KEY = "SPRING_AUTH_JWT_SECRET";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractRole(String token) {
        return extractClaim(token, (claims) -> (String)claims.get("role"));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                //signingKey is the secret used to digitally sign the JWT , used to create signature part of jwt
                .setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public void setCustomClaim(String key, Object value){
        claims.put(key, value);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {

        // ici claims sont les extraclaims qu'on veut stocker dans payload
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setSubject(UserRole.COMPANY.name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean isValidateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean validateTokenCompany(String token, UserDetails companyDetails) {
        final String username = extractUsername(token);
        return (username.equals(companyDetails.getUsername()) && !isTokenExpired(token));
    }

}
