package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.*;
import com.uur.Authentications.dtos.CreateUserAuthorityDto;
import com.uur.Authentications.dtos.UserAuthorityDto;
import com.uur.Authentications.entities.*;
import com.uur.Authentications.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserAuthorityService implements IUserAuthorityService {
    private final UserAuthorityRepository _userAuthorityRepository;
    private final AuthorityRepository  _authorityRepository;
    private final UserRepository _userRepository;

    public UserAuthorityService(UserAuthorityRepository _userAuthorityRepository, AuthorityRepository _authorityRepository, UserRepository _userRepository) {
        this._userAuthorityRepository = _userAuthorityRepository;
        this._authorityRepository = _authorityRepository;
        this._userRepository = _userRepository;
    }

    @Override
    public List<UserAuthorityDto> getUserAuthorities(long userId) {
        User user = _userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı!"));
        return _userAuthorityRepository.findByUser(user).stream()
                .map(userAuthority ->
                        new UserAuthorityDto(userAuthority.getId(), userAuthority.getAuthority().getId(), userAuthority.getAuthority().getName(), userAuthority.getUser().getId())
                )
                .toList();
    }

    @Override
    public UserAuthorityDto AddUserAuthority(CreateUserAuthorityDto createUserAuthorityDto) {
        User addingUser = _userRepository.findById(createUserAuthorityDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı!"));
        Authority addingAuthority = _authorityRepository.findById(createUserAuthorityDto.getAuthorityId()).orElseThrow(() -> new EntityNotFoundException("Authority bulunamadı!"));
        if (_userAuthorityRepository.existsByUserAndAuthority(addingUser, addingAuthority)) {
            throw new BadRequestException("Authority zaten mevcut tekrar eklenemez!");
        } else {
            UserAuthority userAuthority = new UserAuthority();
            userAuthority.setAuthority(addingAuthority);
            userAuthority.setUser(addingUser);
            userAuthority = _userAuthorityRepository.save(userAuthority);
            return new UserAuthorityDto(userAuthority.getId(), userAuthority.getAuthority().getId(), userAuthority.getAuthority().getName(), userAuthority.getUser().getId());
        }
    }

    @Override
    public void RemoveUserAuthority(long userAuthorityId) {
        Optional<UserAuthority> userAuthority = _userAuthorityRepository.findById(userAuthorityId);
        userAuthority.ifPresent(
                _userAuthorityRepository::delete);
    }
}
