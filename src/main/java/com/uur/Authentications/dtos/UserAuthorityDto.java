package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAuthorityDto {
    private long id;
    private long authorityId;
    private String authorityName;
    private long userId;
}
