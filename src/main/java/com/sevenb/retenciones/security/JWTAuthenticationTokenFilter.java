package com.sevenb.retenciones.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevenb.retenciones.entity.User;
import com.sevenb.retenciones.utils.JWTConstants;

/**
 * Authentication filter for non restricted paths
 */
public class JWTAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationTokenFilter.class);

    public JWTAuthenticationTokenFilter() {
        super(JWTConstants.PATH_AUTHORIZATION_TOKEN_FILTER);
    }

    public JWTAuthenticationTokenFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public JWTAuthenticationTokenFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {

        InputStream body = request.getInputStream();
        User user = new ObjectMapper().readValue(body, User.class);
        return getAuthenticationManager().authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
            )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        JWTUtils.addAuthentication(response, authResult.getName());
    }
}
