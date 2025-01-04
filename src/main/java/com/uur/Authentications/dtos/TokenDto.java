package com.uur.Authentications.dtos;


public class TokenDto {
    private String AccessToken;
    private String RefreshToken;
    private long UserId;

    public TokenDto(String accessToken, String refreshToken, long userId) {
        AccessToken = accessToken;
        RefreshToken = refreshToken;
        UserId = userId;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }

    public long getUserId() {
        return UserId;
    }
}
