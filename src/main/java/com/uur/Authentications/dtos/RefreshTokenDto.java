package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;


public class RefreshTokenDto {
    @NotBlank(message = "Token boş bırakılamaz!" )
    private String token;
    @NotBlank(message = "User id boş bırakılamaz!" )
    private long userId;

    public RefreshTokenDto() {
    }

    public @NotBlank(message = "Token boş bırakılamaz!") String getToken() {
        return token;
    }

    public void setToken(@NotBlank(message = "Token boş bırakılamaz!") String token) {
        this.token = token;
    }

    @NotBlank(message = "User id boş bırakılamaz!")
    public long getUserId() {
        return userId;
    }

    public void setUserId(@NotBlank(message = "User id boş bırakılamaz!") long userId) {
        this.userId = userId;
    }
}
