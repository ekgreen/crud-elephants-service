package com.goodboy.edu.crud.elephants.service.api.definitions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityDefinitions {
    public static final String SECRET = "secret";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/v1/sign-up";
    public static final String ABOUT_URL = "/about";
}