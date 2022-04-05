package com.sevenb.retenciones.utils;

/**
 * Constants used with JWT classes and interfaces
 */
public class JWTConstants {

    public final static String YOUR_SECRET = "your_secret";
    public final static String USER_ID = "userId";
    public final static String ROLE = "role";
    public final static String DEFAULT_ROLE = "ADMIN";
    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String BEARER_TOKEN = "Bearer ";
    public final static String PATH_AUTHORIZATION_TOKEN_FILTER = "**/retenciones/users/**";
    public final static String PATH_LOGIN = "/users/login";
    public final static int EXPIRATION_TIME = 1200000;
}
