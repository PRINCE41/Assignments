package com.apica.UserMngService.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.apica.UserMngService.config.CommonProp.JwtProp;

@Slf4j
@Service
public class JwtService {

    private JwtProp jwtProp;

    public JwtService(JwtProp jwtProp){
        this.jwtProp = jwtProp;
    }
    

    public String extractUsername(String token) {
        log.info("Inside extractUsername with token:{}", token);
        String extractedData = extractClaim(token, Claims::getSubject);
        log.info("Exiting extractUsername with extractedData:{}", extractedData);
        return extractedData;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        log.info("Inside extractClaim with token:{} claimsResolver:{}", token, claimsResolver);
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        log.info("Inside generateToken-1Arg with UserDetails:{}", userDetails);
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        log.info("Inside generateToken-2Arg with UserDetails:{}", userDetails);
        return buildToken(extraClaims, userDetails, jwtProp.getExpirationTime());
    }

    public long getExpirationTime() {
        log.info("Inside getExpirationTime");
        return jwtProp.getExpirationTime();
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        log.info("Inside buildToken");
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        log.info("Inside isTokenValid with UserDetails:{}", userDetails);
        final String username = extractUsername(token);
        String expectedUserName = userDetails.getUsername();
        log.info("Expected username:{} IsTokenExpired:{}", expectedUserName, isTokenExpired(token));
        return (username.equals(expectedUserName)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        log.info("Inside isTokenExpired");
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        log.info("Inside extractExpiration");
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        log.info("Inside extractAllClaims");
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        log.info("Inside getSignInKey");
        byte[] keyBytes = Decoders.BASE64.decode(jwtProp.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}