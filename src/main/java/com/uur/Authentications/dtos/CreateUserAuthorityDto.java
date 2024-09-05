package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserAuthorityDto {
    @NotNull(message = "Authority id boş bırakılamaz!" )
    private long authorityId;
    @NotNull(message = "User id boş bırakılamaz!" )
    private long userId;
}
