package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateRoleDto {
    @NotBlank(message = "Rol adı boş bırakılamaz!" )
    private String name;
}
