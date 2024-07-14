package com.uur.Authentications.business;

import com.uur.Authentications.dtos.CreateUserAuthorityDto;
import com.uur.Authentications.dtos.UserAuthorityDto;

import java.util.List;

public interface IUserAuthorityService {
    List<UserAuthorityDto> getUserAuthorities(int userId);

    UserAuthorityDto AddUserAuthority(CreateUserAuthorityDto createUserAuthorityDto);

    void RemoveUserAuthority(int userAuthorityId);
}
