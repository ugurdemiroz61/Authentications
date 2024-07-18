package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.dtos.LoginDto;
import com.uur.Authentications.dtos.RefreshTokenDto;
import com.uur.Authentications.dtos.TokenDto;
import com.uur.Authentications.entities.RefreshToken;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.exeptions.NotFoundException;
import com.uur.Authentications.exeptions.UnAuthorizeException;
import com.uur.Authentications.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final AuthenticationManager _authenticationManager;

    private final JwtTokenProvider _jwtTokenProvider;

    private final TokenService _refreshTokenService;

    private final UserRepository _userRepository;

    @Override
    public TokenDto CreateToken(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());
        Authentication auth = _authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = _userRepository.findByUserName(loginDto.getUserName()).orElseThrow(
                () -> new NotFoundException("Kullanıcı bulunamadı!")
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
    public TokenDto CreateTokenByRefreshToken(RefreshTokenDto refreshTokenDto) {
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
