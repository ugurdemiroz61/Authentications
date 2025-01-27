package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.RefreshTokenRepository;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.entities.RefreshToken;
import com.uur.Authentications.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Slf4j
@Service
public class TokenService implements ITokenService {

    private final RefreshTokenRepository _refreshTokenRepository;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public TokenService(RefreshTokenRepository _refreshTokenRepository) {
        this._refreshTokenRepository = _refreshTokenRepository;
    }

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

    public RefreshToken getByUser(long userId) {
        return _refreshTokenRepository.findByUser_Id(userId).orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı!"));
    }

    public void RevokeRefreshToken(RefreshTokenDto refreshTokenDto) {
        RefreshToken tokenEntity = _refreshTokenRepository.findByUserIdAndToken(refreshTokenDto.getUserId(), refreshTokenDto.getToken()).orElseThrow(() -> new EntityNotFoundException("Refresh Token not found!"));
        _refreshTokenRepository.delete(tokenEntity);
    }

}