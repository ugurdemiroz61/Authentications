package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserDto {
    @NotNull(message = "User id boş bırakılamaz!" )
    private int id;
    @NotBlank(message = "Adı boş bırakılamaz!")
    private String name;
    @NotBlank(message = "Soyadı boş bırakılamaz!")
    private String surname;
    private String phoneNumber;
}
