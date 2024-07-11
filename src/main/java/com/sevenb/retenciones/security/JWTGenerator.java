package com.sevenb.retenciones.security;

import java.util.Date;

import org.springframework.stereotype.Component;
import com.sevenb.retenciones.security.entity.JWTUser;
import com.sevenb.retenciones.utils.JWTConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Generates JWT
 */
@Component
public class JWTGenerator {

    public String generate(JWTUser jwtUser) {
        Claims claims = Jwts.claims()
            .setSubject(jwtUser.getUsername())
                .setSubject(jwtUser.getUsername())
                        .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + JWTConstants.EXPIRATION_TIME));
        claims.put(JWTConstants.USER_ID, String.valueOf(jwtUser.getId()));
        claims.put(JWTConstants.ROLE, jwtUser.getRole());
        claims.put(JWTConstants.COMPANY, jwtUser.getCompany());

        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, JWTConstants.YOUR_SECRET)
            .compact();
    }
}
