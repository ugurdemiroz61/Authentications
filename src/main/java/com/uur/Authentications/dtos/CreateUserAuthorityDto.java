package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserAuthorityDto {
    @NotNull(message = "Authority id boş bırakılamaz!" )
    private int authorityId;
    @NotNull(message = "User id boş bırakılamaz!" )
    private int userId;
}