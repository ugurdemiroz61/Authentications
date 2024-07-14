package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.AuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserAuthorityRepository;
import com.uur.Authentications.dtos.AuthorityDto;
import com.uur.Authentications.dtos.CreateAuthorityDto;
import com.uur.Authentications.entities.Authority;
import com.uur.Authentications.exeptions.BadRequestException;
import com.uur.Authentications.exeptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorityService implements IAuthorityService {
    private final AuthorityRepository _authorityRepository;
    private final UserAuthorityRepository _userAuthorityRepository;
    private final ModelMapper _modelMapper;

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
    public void RemoveAuthority(int authorityId) {
        if (!_userAuthorityRepository.existsById(authorityId)) {
            if (_authorityRepository.existsById(authorityId)) {
                _authorityRepository.deleteById(authorityId);
            } else {
                throw new NotFoundException("Authority Id:" + authorityId + " bulunamadı!");
            }
        } else {
            throw new BadRequestException("Silmek istediğiniz Authority diğer kullanıcılara tanımlıdır silinemez!");
        }
    }
}
