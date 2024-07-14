package com.uur.Authentications.business;

import com.uur.Authentications.dtos.CreateUserRoleDto;
import com.uur.Authentications.dtos.UserRoleDto;

import java.util.List;

public interface IUserRoleService {

    UserRoleDto AddUserRole(CreateUserRoleDto createUserRoleDto);

    void RemoveUserRole(int userRoleId);

    List<UserRoleDto> GetUserRoles(int  userId);
}
