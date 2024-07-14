package com.uur.Authentications.business;

import com.uur.Authentications.dtos.LoginDto;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.dtos.TokenDto;

public interface IAuthService {
    TokenDto CreateToken(LoginDto loginDto);

    void RevokeRefreshToken(RefreshTokenDto refreshTokenDto);

    TokenDto CreateTokenByRefreshToken(RefreshTokenDto refreshTokenDto);
}
