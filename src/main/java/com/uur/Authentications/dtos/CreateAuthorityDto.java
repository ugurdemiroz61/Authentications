package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;

public class CreateAuthorityDto {
    @NotBlank(message = "Authority adı boş bırakılamaz!" )
    private String name;

    public CreateAuthorityDto() {
    }

    public CreateAuthorityDto(String name) {
        this.name = name;
    }

    public @NotBlank(message = "Authority adı boş bırakılamaz!") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Authority adı boş bırakılamaz!") String name) {
        this.name = name;
    }
}
