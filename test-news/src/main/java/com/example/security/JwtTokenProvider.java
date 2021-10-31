package com.example.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;



@Component
public class JwtTokenProvider {
    private static final String secretKey = "secretNewsKey";

    public String generateToken(UUID userId) {
        Date date = new Date();
        long tokenExpireTime = 604800000L;
        long l = date.getTime() + tokenExpireTime;
        Date expireDate = new Date(l);

        return Jwts
                .builder()
                .setSubject(userId.toString())
                .setIssuedAt(date)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.err.println("Muddati o'tgan");
        } catch (MalformedJwtException malformedJwtException) {
            System.err.println("Buzilgan token");
        } catch (SignatureException s) {
            System.err.println("Kalit so'z xato");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            System.err.println("Qo'llanilmagan token");
        } catch (IllegalArgumentException ex) {
            System.err.println("Bo'sh token");
        }
        return false;
    }

    public String getUserIdFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
