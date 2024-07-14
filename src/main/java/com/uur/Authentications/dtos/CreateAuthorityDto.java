package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateAuthorityDto {
    @NotBlank(message = "Authority adı boş bırakılamaz!" )
    private String name;
}
