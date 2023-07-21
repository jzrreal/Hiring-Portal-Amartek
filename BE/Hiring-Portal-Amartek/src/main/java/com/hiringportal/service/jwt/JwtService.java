package com.hiringportal.service.jwt;

import com.hiringportal.entities.Token;
import com.hiringportal.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${JWT_SIGNATURE_KEY}")
    private String SECRET_KEY;

    private final TokenRepository tokenRepository;

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2)))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        String username = extractUsername(jwtToken);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Boolean isTokenExpired(String jwtToken) {
        Boolean result = extractClaim(jwtToken, Claims::getExpiration).before(new Date());
        if (result){
            Token token = tokenRepository.findByToken(jwtToken).orElse(null);
            token.setExpired(true);
            tokenRepository.save(token);
        }
        return result;
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

}
