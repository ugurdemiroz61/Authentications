package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRoleDto {
    private int id;
    private int roleId;
    private String roleName;
    private int userId;
}
