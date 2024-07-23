package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRoleDto {
    private long id;
    private long roleId;
    private String roleName;
    private long userId;
}
