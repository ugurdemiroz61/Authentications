package com.uur.Authentications.security;

import com.uur.Authentications.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {


    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String generateJwtToken(User user) {
        Date expireDate = new Date(new Date().getTime() + jwtExpiration);
        return buildToken(new HashMap<>(), user, expireDate);
    }

    public String generateJwtRefreshToken(User user) {
        Date expireDate = new Date(new Date().getTime() + refreshExpiration);
        return buildToken(new HashMap<>(), user, expireDate);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            User user,
            Date expireDate
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(Long.toString(user.getId()))
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(getSecretKey())
                .compact();
    }

    //BEAN YAPILACAK
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //BEAN YAPILACAK
    private JwtParser getJwtParser() {
        return Jwts.parser().verifyWith(getSecretKey()).build();
    }

    public long getUserIdFromJwt(String token) {
        Claims claims = getJwtParser().parseSignedClaims(token).getPayload();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            getJwtParser().parseSignedClaims(token);
            return !isTokenExpired(token);
        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    private boolean isTokenExpired(String token) {
        Date expiration = getJwtParser().parseSignedClaims(token).getPayload().getExpiration();
        return expiration.before(new Date());
    }
}