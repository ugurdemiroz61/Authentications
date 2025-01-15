package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.dtos.LoginDto;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.dtos.TokenDto;
import com.uur.Authentications.entities.RefreshToken;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.exceptions.UnAuthorizeException;
import com.uur.Authentications.security.JwtTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService implements IAuthService {
    private final AuthenticationManager _authenticationManager;

    private final JwtTokenProvider _jwtTokenProvider;

    private final ITokenService _refreshTokenService;

    private final IUserService _userService;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, ITokenService refreshTokenService, UserRepository userRepository, IUserService userService) {
        _authenticationManager = authenticationManager;
        _jwtTokenProvider = jwtTokenProvider;
        _refreshTokenService = refreshTokenService;
        _userService = userService;
    }

    @Override
    public TokenDto CreateToken(LoginDto loginDto) throws UnAuthorizeException {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());
        Authentication auth = null;
        try {
            auth = _authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            _userService.badCredentials(loginDto.getUserName());
            throw new UnAuthorizeException(e.getMessage());
        } catch (Exception e) {
            throw new UnAuthorizeException(e.getMessage());
        }
        _userService.successCredentials(loginDto.getUserName());

        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = _userService.findByUserName(loginDto.getUserName()).orElseThrow(
                () -> new EntityNotFoundException("Kullanıcı bulunamadı!")
        );

        String jwtToken = _jwtTokenProvider.generateJwtToken(user);
        String jwtRefreshToken = _jwtTokenProvider.generateJwtRefreshToken(user);
        _refreshTokenService.createRefreshToken(user, jwtRefreshToken);
        return new TokenDto("Bearer " + jwtToken, jwtRefreshToken, user.getId());
    }

    @Override
    public void RevokeRefreshToken(RefreshTokenDto refreshTokenDto) {
        _refreshTokenService.RevokeRefreshToken(refreshTokenDto);
    }

    @Override
    public TokenDto CreateTokenByRefreshToken(RefreshTokenDto refreshTokenDto) throws UnAuthorizeException {
        RefreshToken token = _refreshTokenService.getByUser(refreshTokenDto.getUserId());
        if (token.getToken().equals(refreshTokenDto.getToken()) && !_refreshTokenService.isRefreshExpired(token)) {
            User user = token.getUser();
            String jwtToken = _jwtTokenProvider.generateJwtToken(user);
            String jwtRefreshToken = _jwtTokenProvider.generateJwtRefreshToken(user);
            _refreshTokenService.createRefreshToken(user, jwtRefreshToken);
            return new TokenDto("Bearer " + jwtToken, jwtRefreshToken, user.getId());
        } else {
            throw new UnAuthorizeException("Refresh token is not valid.");
        }
    }
}
