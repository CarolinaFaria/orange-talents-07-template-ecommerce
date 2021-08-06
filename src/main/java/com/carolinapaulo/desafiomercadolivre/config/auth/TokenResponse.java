package com.carolinapaulo.desafiomercadolivre.config.auth;

public class TokenResponse {

    private final String tokenType;
    private final String token;

    public TokenResponse(String tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getToken() {
        return token;
    }

}
