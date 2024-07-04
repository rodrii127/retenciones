package com.sevenb.retenciones.utils;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevenb.retenciones.dto.BearerTokenPayloadDto;

/**
 * Util class used in order to obtain information from bearer token
 */
@Component
public class JWTExtractionUtil {

    private static final String BEARER = "Bearer ";
    private static final String SPLIT = "\\.";

    private final ObjectMapper objectMapper;

    public JWTExtractionUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public BearerTokenPayloadDto getPayloadFromToken(String bearerToken) {
        try {
            bearerToken = bearerToken.replace(BEARER, StringUtils.EMPTY);
            String[] chunks = bearerToken.split(SPLIT);
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[NumberUtils.INTEGER_ONE]));
            return objectMapper.readValue(payload, BearerTokenPayloadDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
