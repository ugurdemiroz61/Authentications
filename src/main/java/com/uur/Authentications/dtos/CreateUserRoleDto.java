package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotNull;

public class CreateUserRoleDto {
    @NotNull(message = "Role id boş bırakılamaz!" )
    private long roleId;
    @NotNull(message = "User id boş bırakılamaz!" )
    private long userId;

    public CreateUserRoleDto() {
    }

    public CreateUserRoleDto(long roleId, long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    @NotNull(message = "Role id boş bırakılamaz!")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(@NotNull(message = "Role id boş bırakılamaz!") long roleId) {
        this.roleId = roleId;
    }

    @NotNull(message = "User id boş bırakılamaz!")
    public long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User id boş bırakılamaz!") long userId) {
        this.userId = userId;
    }
}
