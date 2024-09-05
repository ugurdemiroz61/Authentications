package com.uur.Authentications.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserDto {
    @NotBlank(message = "Adı boş bırakılamaz!")
    private String name;
    @NotBlank(message = "Soyadı boş bırakılamaz!")
    private String surname;
    @NotBlank(message = "Kullanıcı adı boş bırakılamaz!" )
    private String userName;
    @Pattern( message = "Lütfen şifreleme kurallarına uygun şifre giriniz!", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;
    @NotBlank(message = "mail adresi boş bırakılamaz!")
    @Email(message = "mail adresi geçerli değil")
    private String email;

    private String phoneNumber;
}
