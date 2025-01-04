package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;

public class CreateRoleDto {
    @NotBlank(message = "Rol adı boş bırakılamaz!" )
    private String name;

    public CreateRoleDto() {
    }

    public CreateRoleDto(String name) {
        this.name = name;
    }

    public @NotBlank(message = "Rol adı boş bırakılamaz!") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Rol adı boş bırakılamaz!") String name) {
        this.name = name;
    }
}
