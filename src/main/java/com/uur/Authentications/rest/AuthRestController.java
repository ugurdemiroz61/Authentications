package com.uur.Authentications.rest;

import com.uur.Authentications.business.IAuthService;
import com.uur.Authentications.dtos.LoginDto;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.dtos.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthRestController {
    private final IAuthService _authService;

    public AuthRestController(IAuthService authService) {
        _authService = authService;
    }

    @PostMapping("CreateToken")
    public ResponseEntity<TokenDto> CreateToken(@RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_authService.CreateToken(loginDto));
    }

    @PostMapping("CreateTokenByRefreshToken")
    public ResponseEntity<TokenDto> CreateTokenByRefreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_authService.CreateTokenByRefreshToken(refreshTokenDto));
    }

    @PostMapping("RevokeRefreshToken")
    public ResponseEntity<Void> RevokeRefreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        _authService.RevokeRefreshToken(refreshTokenDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
