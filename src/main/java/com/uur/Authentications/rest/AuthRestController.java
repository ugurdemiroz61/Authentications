package com.uur.Authentications.rest;

import com.uur.Authentications.business.IAuthService;
import com.uur.Authentications.dtos.LoginDto;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.dtos.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthRestController {
    private final IAuthService _authService;

    public AuthRestController(IAuthService authService) {
        _authService = authService;
    }


    @PostMapping("CreateToken")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto CreateToken(@RequestBody LoginDto loginDto) {
        return _authService.CreateToken(loginDto);
    }

    @PostMapping("CreateTokenByRefreshToken")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto CreateTokenByRefreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return _authService.CreateTokenByRefreshToken(refreshTokenDto);
    }

    @PostMapping("RevokeRefreshToken")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void RevokeRefreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        _authService.RevokeRefreshToken(refreshTokenDto);
    }
}
