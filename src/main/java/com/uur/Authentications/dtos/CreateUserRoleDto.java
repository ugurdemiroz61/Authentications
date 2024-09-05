package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRoleDto {
    @NotNull(message = "Role id boş bırakılamaz!" )
    private long roleId;
    @NotNull(message = "User id boş bırakılamaz!" )
    private long userId;
}
