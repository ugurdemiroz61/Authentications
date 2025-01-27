package com.uur.Authentications.business;

import com.uur.Authentications.dtos.CreateUserRoleDto;
import com.uur.Authentications.dtos.UserRoleDto;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.entities.UserRole;

import java.util.List;

public interface IUserRoleService {

    UserRoleDto AddUserRole(CreateUserRoleDto createUserRoleDto);

    void RemoveUserRole(long userRoleId);

    List<UserRoleDto> GetUserRoles(long  userId);

}
