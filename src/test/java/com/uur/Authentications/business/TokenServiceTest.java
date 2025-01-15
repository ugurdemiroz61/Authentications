package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.RefreshTokenRepository;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.entities.RefreshToken;
import com.uur.Authentications.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TokenServiceTest {

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRefreshToken_ExistingToken() {
        // Arrange
        User user = new User();
        user.setId(1L);

        RefreshToken existingToken = new RefreshToken();
        existingToken.setToken("oldToken");
        existingToken.setUser(user);

        when(refreshTokenRepository.findByUser_Id(user.getId())).thenReturn(Optional.of(existingToken));

        // Act
        tokenService.createRefreshToken(user, "newToken");

        // Assert
        assertEquals("newToken", existingToken.getToken());
        verify(refreshTokenRepository, times(1)).save(existingToken);
    }

    @Test
    public void testCreateRefreshToken_NewToken() {
        // Arrange
        User user = new User();
        user.setId(1L);

        when(refreshTokenRepository.findByUser_Id(user.getId())).thenReturn(Optional.empty());

        // Act
        tokenService.createRefreshToken(user, "newToken");

        // Assert
        verify(refreshTokenRepository, times(1)).save(any(RefreshToken.class));
    }

    @Test
    public void testIsRefreshExpired_NotExpired() {
        // Arrange
        RefreshToken token = new RefreshToken();
        token.setExpiryDate(new Date(new Date().getTime() + 10000)); // 10 saniye sonra

        // Act
        boolean isExpired = tokenService.isRefreshExpired(token);

        // Assert
        assertFalse(isExpired);
    }

    @Test
    public void testIsRefreshExpired_Expired() {
        // Arrange
        RefreshToken token = new RefreshToken();
        token.setExpiryDate(new Date(new Date().getTime() - 10000)); // 10 saniye önce

        // Act
        boolean isExpired = tokenService.isRefreshExpired(token);

        // Assert
        assertTrue(isExpired);
    }

    @Test
    public void testGetByUser_Success() {
        // Arrange
        User user = new User();
        user.setId(1L);
        RefreshToken token = new RefreshToken();
        token.setUser(user);

        when(refreshTokenRepository.findByUser_Id(user.getId())).thenReturn(Optional.of(token));

        // Act
        RefreshToken result = tokenService.getByUser(user.getId());

        // Assert
        assertEquals(token, result);
    }

    @Test
    public void testGetByUser_NotFound() {
        // Arrange
        long userId = 1L;
        when(refreshTokenRepository.findByUser_Id(userId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tokenService.getByUser(userId);
        });

        assertEquals("Kullanıcı bulunamadı!", exception.getMessage());
    }

    @Test
    public void testRevokeRefreshToken_Success() {
        // Arrange
        long userId = 1L;
        String token = "refreshToken";
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setUserId(userId);
        refreshTokenDto.setToken(token);

        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setToken(token);

        when(refreshTokenRepository.findByUserIdAndToken(userId, token)).thenReturn(Optional.of(refreshTokenEntity));

        // Act
        tokenService.RevokeRefreshToken(refreshTokenDto);

        // Assert
        verify(refreshTokenRepository, times(1)).delete(refreshTokenEntity);
    }

    @Test
    public void testRevokeRefreshToken_NotFound() {
        // Arrange
        long userId = 1L;
        String token = "refreshToken";
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setUserId(userId);
        refreshTokenDto.setToken(token);

        when(refreshTokenRepository.findByUserIdAndToken(userId, token)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tokenService.RevokeRefreshToken(refreshTokenDto);
        });

        assertEquals("Refresh Token not found!", exception.getMessage());
    }
}