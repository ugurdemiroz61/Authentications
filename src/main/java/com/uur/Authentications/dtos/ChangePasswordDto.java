package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordDto {
    @NotBlank(message = "Kullanıcı adı boş bırakılamaz!" )
    private String userName;
    @NotBlank(message = "Şifre boş bırakılamaz!" )
    private String currentPassword;
    @NotBlank(message = "Yeni şifre boş bırakılamaz!" )
    @Pattern( message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String newPassword;
}
