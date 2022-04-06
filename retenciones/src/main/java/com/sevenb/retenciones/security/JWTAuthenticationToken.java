package com.sevenb.retenciones.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * JWT Authentication Token component
 */
@Component
public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = -2501404618575586949L;

    private String token;

    public JWTAuthenticationToken() {
        super(null, null);
    }

    public JWTAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}