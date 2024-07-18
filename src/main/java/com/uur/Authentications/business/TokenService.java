package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.RefreshTokenRepository;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.entities.RefreshToken;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.exeptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    private final RefreshTokenRepository _refreshTokenRepository;

    public TokenService(RefreshTokenRepository _refreshTokenRepository) {
        this._refreshTokenRepository = _refreshTokenRepository;
    }

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public void createRefreshToken(User user, String refreshToken) {
        RefreshToken token = _refreshTokenRepository.findByUser_Id(user.getId()).
                orElse(
                        RefreshToken.builder().user(user).build()
                );
        token.setToken(refreshToken);
        Date expireDate = new Date(new Date().getTime() + refreshExpiration);
        token.setExpiryDate(expireDate);
        _refreshTokenRepository.save(token);
    }

    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().before(new Date());
    }

    public RefreshToken getByUser(int userId) {
        return _refreshTokenRepository.findByUser_Id(userId).orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı!"));
    }

    public void RevokeRefreshToken(RefreshTokenDto refreshTokenDto) {
        RefreshToken tokenEntity = _refreshTokenRepository.findByUserIdAndToken(refreshTokenDto.getUserId(), refreshTokenDto.getToken()).orElseThrow(() -> new NotFoundException("Refresh Token not found!"));
        _refreshTokenRepository.delete(tokenEntity);
    }

}