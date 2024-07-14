package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRoleDto {
    @NotNull(message = "Role id boş bırakılamaz!" )
    private int roleId;
    @NotNull(message = "User id boş bırakılamaz!" )
    private int userId;
}
