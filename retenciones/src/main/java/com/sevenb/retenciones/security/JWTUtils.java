package com.sevenb.retenciones.security;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import com.sevenb.retenciones.config.exception.UnauthorizedException;
import com.sevenb.retenciones.utils.JWTConstants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * Authentication methods for creating and reading JWT
 */
public class JWTUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JWTUtils.class);

    protected static void addAuthentication(HttpServletResponse res, String username) {
        String token = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + JWTConstants.EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, JWTConstants.YOUR_SECRET)
            .compact();
        res.addHeader(JWTConstants.AUTHORIZATION_HEADER, JWTConstants.BEARER_TOKEN + token);
    }

    protected static Authentication getAuthentication(HttpServletRequest request, String[] roles) {
        String token = request.getHeader(JWTConstants.AUTHORIZATION_HEADER);
        if (Objects.nonNull(token)) {
            try {
                String user = Jwts.parser()
                    .setSigningKey(JWTConstants.YOUR_SECRET)
                    .parseClaimsJws(token.replace(JWTConstants.BEARER_TOKEN, StringUtils.EMPTY))
                    .getBody()
                    .getSubject();
                return JWTUtils.setAuthenticationToken(user, roles);
            } catch (ExpiredJwtException e) {
                LOG.error("Error while trying to parse unauthorized response.");
                throw new UnauthorizedException("login-service.token.expired");
            } catch (SignatureException e) {
                LOG.error("Error while trying to parse unauthorized response.");
                throw new UnauthorizedException("login-service.token.wrong-format");
            } catch (Exception e) {
                LOG.error("Error while trying to parse unauthorized response.");
                throw new UnauthorizedException("login-service.token.not-found");
            }
        }
        return null;
    }

    private static UsernamePasswordAuthenticationToken setAuthenticationToken(String user, String[] roles) {
        if (Objects.nonNull(user)) {
            return new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(roles));
        }
        return null;
    }
}