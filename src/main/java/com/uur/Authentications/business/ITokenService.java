package com.uur.Authentications.business;

import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.entities.RefreshToken;
import com.uur.Authentications.entities.User;

public interface ITokenService {
    void createRefreshToken(User user, String refreshToken);
    boolean isRefreshExpired(RefreshToken token);
    RefreshToken getByUser(long userId);
    void RevokeRefreshToken(RefreshTokenDto refreshTokenDto);

}
