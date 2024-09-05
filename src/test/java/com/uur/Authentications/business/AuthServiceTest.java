package com.uur.Authentications.business;
import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.dtos.LoginDto;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.dtos.TokenDto;
import com.uur.Authentications.entities.RefreshToken;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.exceptions.NotFoundException;
import com.uur.Authentications.exceptions.UnAuthorizeException;
import com.uur.Authentications.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private TokenService refreshTokenService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateToken_Success() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUserName("testUser");
        loginDto.setPassword("password");

        User user = new User();
        user.setId(1L);
        user.setUserName("testUser");

        Authentication auth = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(userRepository.findByUserName(loginDto.getUserName())).thenReturn(Optional.of(user));
        when(jwtTokenProvider.generateJwtToken(user)).thenReturn("jwtToken");
        when(jwtTokenProvider.generateJwtRefreshToken(user)).thenReturn("refreshToken");

        // Act
        TokenDto tokenDto = authService.CreateToken(loginDto);

        // Assert
        assertEquals("Bearer jwtToken", tokenDto.getAccessToken());
        assertEquals("refreshToken", tokenDto.getRefreshToken());
        assertEquals(1L, tokenDto.getUserId());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, times(1)).findByUserName(loginDto.getUserName());
        verify(jwtTokenProvider, times(1)).generateJwtToken(user);
        verify(jwtTokenProvider, times(1)).generateJwtRefreshToken(user);
        verify(refreshTokenService, times(1)).createRefreshToken(user, "refreshToken");
    }

    @Test
    public void testCreateToken_UserNotFound() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUserName("unknownUser");
        loginDto.setPassword("password");

        when(userRepository.findByUserName(loginDto.getUserName())).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            authService.CreateToken(loginDto);
        });

        assertEquals("Kullanıcı bulunamadı!", exception.getMessage());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, times(1)).findByUserName(loginDto.getUserName());
        verify(jwtTokenProvider, times(0)).generateJwtToken(any(User.class));
    }

    @Test
    public void testCreateTokenByRefreshToken_Success() {
        // Arrange
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setToken("refreshToken");
        refreshTokenDto.setUserId(1L);

        User user = new User();
        user.setId(1L);
        user.setUserName("testUser");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken("refreshToken");
        refreshToken.setUser(user);

        when(refreshTokenService.getByUser(refreshTokenDto.getUserId())).thenReturn(refreshToken);
        when(refreshTokenService.isRefreshExpired(refreshToken)).thenReturn(false);
        when(jwtTokenProvider.generateJwtToken(user)).thenReturn("newJwtToken");
        when(jwtTokenProvider.generateJwtRefreshToken(user)).thenReturn("newRefreshToken");

        // Act
        TokenDto tokenDto = authService.CreateTokenByRefreshToken(refreshTokenDto);

        // Assert
        assertEquals("Bearer newJwtToken", tokenDto.getAccessToken());
        assertEquals("newRefreshToken", tokenDto.getRefreshToken());

        verify(refreshTokenService, times(1)).getByUser(refreshTokenDto.getUserId());
        verify(refreshTokenService, times(1)).isRefreshExpired(refreshToken);
        verify(jwtTokenProvider, times(1)).generateJwtToken(user);
        verify(jwtTokenProvider, times(1)).generateJwtRefreshToken(user);
        verify(refreshTokenService, times(1)).createRefreshToken(user, "newRefreshToken");
    }

    @Test
    public void testCreateTokenByRefreshToken_InvalidToken() {
        // Arrange
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setToken("invalidToken");
        refreshTokenDto.setUserId(1L);

        User user = new User();
        user.setId(1L);
        user.setUserName("testUser");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken("validToken");
        refreshToken.setUser(user);

        when(refreshTokenService.getByUser(refreshTokenDto.getUserId())).thenReturn(refreshToken);
        when(refreshTokenService.isRefreshExpired(refreshToken)).thenReturn(false);

        // Act & Assert
        UnAuthorizeException exception = assertThrows(UnAuthorizeException.class, () -> {
            authService.CreateTokenByRefreshToken(refreshTokenDto);
        });

        assertEquals("Refresh token is not valid.", exception.getMessage());

        verify(refreshTokenService, times(1)).getByUser(refreshTokenDto.getUserId());
        verify(refreshTokenService, times(0)).createRefreshToken(any(), anyString());
    }

    @Test
    public void testRevokeRefreshToken() {
        // Arrange
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setToken("refreshToken");
        refreshTokenDto.setUserId(1L);

        // Act
        authService.RevokeRefreshToken(refreshTokenDto);

        // Assert
        verify(refreshTokenService, times(1)).RevokeRefreshToken(refreshTokenDto);
    }
}