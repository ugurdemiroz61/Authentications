package com.uur.Authentications.business;

import com.uur.Authentications.dtos.LoginDto;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.dtos.TokenDto;
import com.uur.Authentications.exceptions.UnAuthorizeException;

public interface IAuthService {
    TokenDto CreateToken(LoginDto loginDto) throws UnAuthorizeException;

    void RevokeRefreshToken(RefreshTokenDto refreshTokenDto);

    TokenDto CreateTokenByRefreshToken(RefreshTokenDto refreshTokenDto) throws UnAuthorizeException;
}
