package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
public class ChangePasswordDto {
    @NotBlank(message = "Kullanıcı adı boş bırakılamaz!" )
    private String userName;
    @NotBlank(message = "Şifre boş bırakılamaz!" )
    private String currentPassword;
    @NotBlank(message = "Yeni şifre boş bırakılamaz!" )
    @Pattern( message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String newPassword;

    public ChangePasswordDto() {
    }

    public ChangePasswordDto(String userName, String currentPassword, String newPassword) {
        this.userName = userName;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public @NotBlank(message = "Kullanıcı adı boş bırakılamaz!") String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = "Kullanıcı adı boş bırakılamaz!") String userName) {
        this.userName = userName;
    }

    public @NotBlank(message = "Şifre boş bırakılamaz!") String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(@NotBlank(message = "Şifre boş bırakılamaz!") String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public @NotBlank(message = "Yeni şifre boş bırakılamaz!") @Pattern(message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$") String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(@NotBlank(message = "Yeni şifre boş bırakılamaz!") @Pattern(message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$") String newPassword) {
        this.newPassword = newPassword;
    }
}
