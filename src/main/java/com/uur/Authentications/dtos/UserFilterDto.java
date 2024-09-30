package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterDto {
    private long id;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean lockoutEnabled;
}
