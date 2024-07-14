package com.uur.Authentications.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;

@Entity
@Table( schema = "Authentication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    @Column(nullable = false)
    private Date createdDate;
    @Column(nullable = true)
    private int createUser;

    @Column(nullable = true)
    private Date updatedDate;
    @Column(nullable = true)
    private int updatedUser;

    @OneToMany(fetch = FetchType.EAGER)
    private List<UserRole> userRoleList;

    @OneToMany(fetch = FetchType.EAGER)
    private List<UserAuthority> userAuthorityList;
}
