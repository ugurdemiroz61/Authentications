package com.uur.Authentications.dtos;


public class UserRoleDto {
    private long id;
    private long roleId;
    private String roleName;
    private long userId;

    public UserRoleDto(long id, long roleId, String roleName, long userId) {
        this.id = id;
        this.roleId = roleId;
        this.roleName = roleName;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public long getUserId() {
        return userId;
    }
}
