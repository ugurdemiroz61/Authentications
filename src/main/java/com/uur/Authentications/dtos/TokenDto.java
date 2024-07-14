package com.uur.Authentications.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class TokenDto {
    private String AccessToken;
    private String RefreshToken;
    private int UserId;
}
