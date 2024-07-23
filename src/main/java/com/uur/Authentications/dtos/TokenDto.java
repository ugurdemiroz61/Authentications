package com.uur.Authentications.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDto {
    private String AccessToken;
    private String RefreshToken;
    private long UserId;
}
