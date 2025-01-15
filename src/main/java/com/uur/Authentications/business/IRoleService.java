package com.uur.Authentications.business;

import com.uur.Authentications.dtos.CreateRoleDto;
import com.uur.Authentications.dtos.RoleDto;
import com.uur.Authentications.entities.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    RoleDto AddRole(CreateRoleDto roleDto);

    void RemoveRole(long roleId);

    List<RoleDto> findAll();
}
