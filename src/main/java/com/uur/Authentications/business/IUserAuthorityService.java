package com.uur.Authentications.business;

import com.uur.Authentications.dtos.CreateUserAuthorityDto;
import com.uur.Authentications.dtos.UserAuthorityDto;

import java.util.List;

public interface IUserAuthorityService {
    List<UserAuthorityDto> getUserAuthorities(long userId);

    UserAuthorityDto AddUserAuthority(CreateUserAuthorityDto createUserAuthorityDto);

    void RemoveUserAuthority(long userAuthorityId);
}
