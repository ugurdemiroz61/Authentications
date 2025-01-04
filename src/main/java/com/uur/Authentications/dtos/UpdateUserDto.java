package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateUserDto {
    @NotNull(message = "User id boş bırakılamaz!" )
    private long id;
    @NotBlank(message = "Adı boş bırakılamaz!")
    private String name;
    @NotBlank(message = "Soyadı boş bırakılamaz!")
    private String surname;
    private String phoneNumber;

    public UpdateUserDto() {
    }

    public UpdateUserDto(long id, String name, String surname, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    @NotNull(message = "User id boş bırakılamaz!")
    public long getId() {
        return id;
    }

    public void setId(@NotNull(message = "User id boş bırakılamaz!") long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
