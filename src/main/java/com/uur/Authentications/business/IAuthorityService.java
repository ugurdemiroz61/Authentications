package com.uur.Authentications.business;

import com.uur.Authentications.dtos.AuthorityDto;
import com.uur.Authentications.dtos.CreateAuthorityDto;
import com.uur.Authentications.entities.Authority;

import java.util.List;
import java.util.Optional;

public interface IAuthorityService {
    List<AuthorityDto> findAll();

    AuthorityDto AddAuthority(CreateAuthorityDto authorityDto);

    void RemoveAuthority(long authorityId);
}
