package com.sevenb.retenciones.config.cors;

import org.apache.commons.lang3.StringUtils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:cors.properties"})
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cors.configuration.allowed")
public class CustomCorsConfiguration {

    private static final String ANY = "*";
    private static final String DEFAULT_METHODS = "OPTIONS,GET,POST,PUT,DELETE";

    private String origins;
    private String methods;
    private String headers;

    public String getOrigins() {
        return StringUtils.isNotEmpty(origins) ? origins : ANY;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getMethods() {
        return StringUtils.isNotEmpty(methods) ? methods : DEFAULT_METHODS;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getHeaders() {
        return StringUtils.isNotEmpty(headers) ? headers : ANY;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "{\"CustomConfiguration\":{"
            + " \"origins\":\"" + origins + "\""
            + ", \"methods\":\"" + methods + "\""
            + ", \"headers\":\"" + headers + "\""
            + "}}";
    }
}
