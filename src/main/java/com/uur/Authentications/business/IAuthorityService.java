package com.uur.Authentications.business;

import com.uur.Authentications.dtos.AuthorityDto;
import com.uur.Authentications.dtos.CreateAuthorityDto;

import java.util.List;

public interface IAuthorityService {
    List<AuthorityDto> findAll();

    AuthorityDto AddAuthority(CreateAuthorityDto authorityDto);

    void RemoveAuthority(int authorityId);
}
