package com.uur.Authentications.business;

import com.uur.Authentications.dtos.CreateRoleDto;
import com.uur.Authentications.dtos.RoleDto;
import java.util.List;

public interface IRoleService {
    RoleDto AddRole(CreateRoleDto roleDto);

    void RemoveRole(int roleId);

    List<RoleDto> findAll();
}
