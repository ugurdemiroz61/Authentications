package com.uur.Authentications.dtos;

import jakarta.validation.constraints.*;


public class CreateUserDto {
    @NotBlank(message = "Adı boş bırakılamaz!")
    private String name;
    @NotBlank(message = "Soyadı boş bırakılamaz!")
    private String surname;
    @NotBlank(message = "Kullanıcı adı boş bırakılamaz!" )
    private String userName;
    @Pattern( message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;
    @NotBlank(message = "mail adresi boş bırakılamaz!")
    @Email(message = "mail adresi geçerli değil")
    private String email;

    private String phoneNumber;

    public CreateUserDto() {
    }

    public CreateUserDto(String name, String surname, String userName, String password, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "Adı boş bırakılamaz!") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Adı boş bırakılamaz!") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Soyadı boş bırakılamaz!") String getSurname() {
        return surname;
    }

    public void setSurname(@NotBlank(message = "Soyadı boş bırakılamaz!") String surname) {
        this.surname = surname;
    }

    public @NotBlank(message = "Kullanıcı adı boş bırakılamaz!") String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = "Kullanıcı adı boş bırakılamaz!") String userName) {
        this.userName = userName;
    }

    public @Pattern(message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$") String password) {
        this.password = password;
    }

    public @NotBlank(message = "mail adresi boş bırakılamaz!") @Email(message = "mail adresi geçerli değil") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "mail adresi boş bırakılamaz!") @Email(message = "mail adresi geçerli değil") String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
