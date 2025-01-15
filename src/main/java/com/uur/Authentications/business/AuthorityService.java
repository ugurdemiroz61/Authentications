package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.AuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserAuthorityRepository;
import com.uur.Authentications.dtos.AuthorityDto;
import com.uur.Authentications.dtos.CreateAuthorityDto;
import com.uur.Authentications.entities.Authority;
import com.uur.Authentications.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorityService implements IAuthorityService {
    private final AuthorityRepository _authorityRepository;
    private final UserAuthorityRepository _userAuthorityRepository;
    private final ModelMapper _modelMapper;


    public AuthorityService(AuthorityRepository _authorityRepository, UserAuthorityRepository _userAuthorityRepository, ModelMapper _modelMapper) {
        this._authorityRepository = _authorityRepository;
        this._userAuthorityRepository = _userAuthorityRepository;
        this._modelMapper = _modelMapper;
    }

    @Override
    public List<AuthorityDto> findAll() {
        List<Authority> authorities = _authorityRepository.findAll();
        return authorities
                .stream()
                .map(user -> _modelMapper.map(user, AuthorityDto.class))
                .toList();
    }

    @Override
    public AuthorityDto AddAuthority(CreateAuthorityDto createAuthorityDto) {
        Optional<Authority> authorityOptional = _authorityRepository.findByName(createAuthorityDto.getName());
        Authority addingAuthority = _modelMapper.map(createAuthorityDto, Authority.class);
        authorityOptional.ifPresentOrElse(
                ExistingAuthority -> {
                    throw new BadRequestException("Authority adı daha önce kullanıldı tekrar eklenemez! " + ExistingAuthority.getId());
                },
                () -> {
                    _authorityRepository.save(addingAuthority);
                });
        return _modelMapper.map(addingAuthority, AuthorityDto.class);
    }

    @Override
    public void RemoveAuthority(long authorityId) {
        if (!_userAuthorityRepository.existsById(authorityId)) {
            if (_authorityRepository.existsById(authorityId)) {
                _authorityRepository.deleteById(authorityId);
            } else {
                throw new EntityNotFoundException("Authority Id:" + authorityId + " bulunamadı!");
            }
        } else {
            throw new BadRequestException("Silmek istediğiniz Authority diğer kullanıcılara tanımlıdır silinemez!");
        }
    }

}
