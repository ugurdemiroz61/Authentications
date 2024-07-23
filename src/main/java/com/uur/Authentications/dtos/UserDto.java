package com.uur.Authentications.dtos;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean lockoutEnabled;
    private List<RoleDto> roles;
    private List<AuthorityDto> authorities;
}
