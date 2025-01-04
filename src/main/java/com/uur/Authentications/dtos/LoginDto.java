package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;


public class LoginDto {
    @NotBlank(message = "Email boş bırakılamaz!" )
    private String userName;
    @NotBlank(message = "Şifre boş bırakılamaz!" )
    private String password;

    public LoginDto() {
    }

    public LoginDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public @NotBlank(message = "Email boş bırakılamaz!") String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = "Email boş bırakılamaz!") String userName) {
        this.userName = userName;
    }

    public @NotBlank(message = "Şifre boş bırakılamaz!") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Şifre boş bırakılamaz!") String password) {
        this.password = password;
    }
}
