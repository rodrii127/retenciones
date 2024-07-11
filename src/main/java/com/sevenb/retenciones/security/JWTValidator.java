package com.sevenb.retenciones.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.security.entity.JWTUser;
import com.sevenb.retenciones.utils.JWTConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Validates provided token, sent as parameter
 */

@Component
public class JWTValidator {

    private static final Logger LOG = LoggerFactory.getLogger(JWTValidator.class);

    public JWTUser validate(String token) {

        JWTUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                .setSigningKey(JWTConstants.YOUR_SECRET)
                .parseClaimsJwt(token)
                .getBody();
            jwtUser = new JWTUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get(JWTConstants.USER_ID)));
            jwtUser.setRole((String) body.get(JWTConstants.ROLE));
            jwtUser.setCompany((Company) body.get(JWTConstants.COMPANY));
        } catch (Exception e) {
            LOG.error(String.format("Token setting error", e));
            throw new NotFoundException("security.JWT-validator.not-found");
        }
        return jwtUser;
    }
}
