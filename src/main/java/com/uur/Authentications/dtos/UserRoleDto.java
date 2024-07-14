package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserRoleDto {
    private int id;
    private int roleId;
    private String roleName;
    private int userId;
}
