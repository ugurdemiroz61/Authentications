package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAuthorityDto {
    private int id;
    private int authorityId;
    private String authorityName;
    private int userId;
}
