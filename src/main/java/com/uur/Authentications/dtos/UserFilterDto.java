package com.uur.Authentications.dtos;

public class UserFilterDto {
    private long id;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean lockoutEnabled;

    public UserFilterDto() {
    }

    public UserFilterDto(long id, String userName, String name, String surname, String email, String phoneNumber, boolean lockoutEnabled) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lockoutEnabled = lockoutEnabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isLockoutEnabled() {
        return lockoutEnabled;
    }

    public void setLockoutEnabled(boolean lockoutEnabled) {
        this.lockoutEnabled = lockoutEnabled;
    }
}
