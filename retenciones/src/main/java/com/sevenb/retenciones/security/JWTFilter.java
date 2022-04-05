package com.sevenb.retenciones.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.sevenb.retenciones.config.exception.UnauthorizedException;
import com.sevenb.retenciones.entity.ApiError;
import com.sevenb.retenciones.utils.JWTConstants;

/**
 * Authentication filter for restricted paths
 */
@Component
public class JWTFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {

        String[] roles = {JWTConstants.DEFAULT_ROLE};

        try {
            Authentication authentication = JWTUtils.getAuthentication((HttpServletRequest) request, roles);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (UnauthorizedException exception) {
            response.setContentType("application/json");
            response.getWriter().write(new ApiError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name(), exception.getMessage()).toString());
        }
    }
}
