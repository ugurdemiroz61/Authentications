package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class ResetPasswordDto {
    @NotBlank(message = "Kullanıcı adı boş bırakılamaz!" )
    private String userName ;
    @NotBlank(message = "Yeni şifre boş bırakılamaz!" )
    @Pattern( message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String newPassword ;

    public ResetPasswordDto() {
    }

    public ResetPasswordDto(String userName, String newPassword) {
        this.userName = userName;
        this.newPassword = newPassword;
    }

    public @NotBlank(message = "Kullanıcı adı boş bırakılamaz!") String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = "Kullanıcı adı boş bırakılamaz!") String userName) {
        this.userName = userName;
    }

    public @NotBlank(message = "Yeni şifre boş bırakılamaz!") @Pattern(message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$") String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(@NotBlank(message = "Yeni şifre boş bırakılamaz!") @Pattern(message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$") String newPassword) {
        this.newPassword = newPassword;
    }
}
