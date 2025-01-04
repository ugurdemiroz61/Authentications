package com.uur.Authentications.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(schema = "Authentication")
public class User extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String surname;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false)
    private Boolean emailConfirmed = false;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = true, length = 50)
    private String phoneNumber;
    @Column(nullable = false)
    private Boolean phoneNumberConfirmed = false;
    @Column(nullable = false)
    private Boolean twoFactorEnabled = false;
    @Column(nullable = false)
    private int accessFailedCount = 0;
    @Column(nullable = true)
    private Date lockoutEnd;
    @Column(nullable = false)
    private boolean lockoutEnabled = false;



    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<UserAuthority> userAuthorities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<RefreshToken> refreshTokens;


    public User() {
    }

    public User(Boolean phoneNumberConfirmed, String name, String surname, String userName, String email, Boolean emailConfirmed, String passwordHash, String phoneNumber, Boolean twoFactorEnabled, int accessFailedCount, Date lockoutEnd, boolean lockoutEnabled, Set<UserRole> userRoles, Set<UserAuthority> userAuthorities, Set<RefreshToken> refreshTokens) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.twoFactorEnabled = twoFactorEnabled;
        this.accessFailedCount = accessFailedCount;
        this.lockoutEnd = lockoutEnd;
        this.lockoutEnabled = lockoutEnabled;
        this.userRoles = userRoles;
        this.userAuthorities = userAuthorities;
        this.refreshTokens = refreshTokens;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getPhoneNumberConfirmed() {
        return phoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
    }

    public Boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public int getAccessFailedCount() {
        return accessFailedCount;
    }

    public void setAccessFailedCount(int accessFailedCount) {
        this.accessFailedCount = accessFailedCount;
    }

    public Date getLockoutEnd() {
        return lockoutEnd;
    }

    public void setLockoutEnd(Date lockoutEnd) {
        this.lockoutEnd = lockoutEnd;
    }

    public boolean isLockoutEnabled() {
        return lockoutEnabled;
    }

    public void setLockoutEnabled(boolean lockoutEnabled) {
        this.lockoutEnabled = lockoutEnabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public Set<RefreshToken> getRefreshTokens() {
        return refreshTokens;
    }

    public void setRefreshTokens(Set<RefreshToken> refreshTokens) {
        this.refreshTokens = refreshTokens;
    }
}
