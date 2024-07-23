package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenDto {
    @NotBlank(message = "Token boş bırakılamaz!" )
    private String token;
    @NotBlank(message = "User id boş bırakılamaz!" )
    private long userId;
}
