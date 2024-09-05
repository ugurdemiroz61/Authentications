package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenDto {
    @NotBlank(message = "Token boş bırakılamaz!" )
    private String token;
    @NotBlank(message = "User id boş bırakılamaz!" )
    private long userId;
}
