package com.sevenb.retenciones.security;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.security.entity.JWTUser;
import com.sevenb.retenciones.security.entity.JWTUserDetails;

/**
 * Sets authentication provider
 */
@Component
public class JWTAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationProvider.class);

    private JWTValidator validator;

    @Autowired
    public JWTAuthenticationProvider(JWTValidator validator) {
        this.validator = validator;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // UNUSED CLASS. CANNOT RETURN NULL ON VOID METHOD
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();
        JWTUser jwtUser = validator.validate(token);

        if (Objects.isNull(jwtUser)) {
            LOG.error(String.format("Token validation failed for jwtUser"));
            throw new NotFoundException("security.JWT-authentication-provider.not-found");
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList(jwtUser.getRole());

        return new JWTUserDetails(jwtUser.getUsername(), token, jwtUser.getId(), grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JWTAuthenticationToken.class.isAssignableFrom(authentication));
    }
}