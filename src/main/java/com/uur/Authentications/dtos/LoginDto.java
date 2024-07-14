package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "Email boş bırakılamaz!" )
    private String userName;
    @NotBlank(message = "Şifre boş bırakılamaz!" )
    private String password;
}
