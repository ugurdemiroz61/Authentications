package com.uur.Authentications.business;


import com.uur.Authentications.JpaRepositories.UserAuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.JpaRepositories.UserRoleRepository;
import com.uur.Authentications.dtos.*;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class UserService implements IUserService {
    private final PasswordEncoder _passwordEncoder;
    private final UserRepository _userRepository;
    private final ModelMapper _modelMapper;
    private final UserRoleRepository _userRoleRepository;
    private final UserAuthorityRepository _userAuthorityRepository;
    private final AuthenticationManager _authenticationManager;

    public UserService(PasswordEncoder _passwordEncoder, UserRepository _userRepository, ModelMapper _modelMapper, UserRoleRepository _userRoleRepository, UserAuthorityRepository _userAuthorityRepository, AuthenticationManager _authenticationManager) {
        this._passwordEncoder = _passwordEncoder;
        this._userRepository = _userRepository;
        this._modelMapper = _modelMapper;
        this._userRoleRepository = _userRoleRepository;
        this._userAuthorityRepository = _userAuthorityRepository;
        this._authenticationManager = _authenticationManager;
    }

    @Override
    public UserDto CreateUser(CreateUserDto createUserDto) {
        if (_userRepository.existsByUserName(createUserDto.getUserName())) {
            throw new BadRequestException("Kullanıcı adı daha önce kullanılmış!");
        } else if (_userRepository.existsByEmail(createUserDto.getEmail())) {
            throw new BadRequestException("Mail adresi daha önce kullanılmış!");
        } else {
            User user = _modelMapper.map(createUserDto, User.class);
            user.setPasswordHash(_passwordEncoder.encode(createUserDto.getPassword()));
            user = _userRepository.save(user);
            return _modelMapper.map(user, UserDto.class);
        }
    }

    @Override
    public void UpdateUser(UpdateUserDto updateUserDto) {
        Optional<User> updatingUser = _userRepository.findById(updateUserDto.getId());
        updatingUser.ifPresentOrElse(user -> {
                    user.setName(updateUserDto.getName());
                    user.setSurname(updateUserDto.getSurname());
                    _userRepository.save(user);
                },
                () -> {
                    throw new BadRequestException("Güncellenecek kullanıcı id bulunamadı! " + updateUserDto.getId());
                }
        );
    }

    @Override
    public UserDto getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = _userRepository.findByUserName(userName).orElseThrow(() ->
                new BadRequestException("Kullanıcı bulunamadı ! " + userName)
        );
        UserDto resultUser = _modelMapper.map(currentUser, UserDto.class);
        resultUser.setRoles(_userRoleRepository.findByUser(currentUser).stream().map(s -> new RoleDto(s.getRole().getId(), s.getRole().getName())).toList());
        resultUser.setAuthorities(_userAuthorityRepository.findByUser(currentUser).stream().map(s -> new AuthorityDto(s.getAuthority().getId(), s.getAuthority().getName())).toList());
        return resultUser;
    }

    public Page<UserDto> getUsers(Pageable pageable,UserFilterDto userFilterDto) {
        User example = _modelMapper.map(userFilterDto, User.class);
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnorePaths("id", " lockoutEnabled", "accessFailedCount", "createUser", "updatedUser")
                .withMatcher("name", startsWith().ignoreCase())
                .withMatcher("surname", startsWith().ignoreCase())
                .withMatcher("email", startsWith().ignoreCase())
                .withMatcher("lockoutEnabled", exact())
                .withMatcher("phoneNumber", startsWith().ignoreCase());

        Page<User> res = _userRepository.findAll(Example.of(example, matcher), pageable);

        return new PageImpl<>(res.stream().map(s -> _modelMapper.map(s, UserDto.class)).toList(), pageable, res.getTotalElements());
    }

    @Override
    public void ChangePassword(ChangePasswordDto changePasswordDto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!changePasswordDto.getUserName().equals(userName)) {
            throw new BadRequestException("Kullanıcı sadece kendi şifresini değiştirebilir!");
        }
        Optional<User> optUser = _userRepository.findByUserName(changePasswordDto.getUserName());
        optUser.ifPresentOrElse(user -> {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUserName(), changePasswordDto.getCurrentPassword());
                    Authentication auth = _authenticationManager.authenticate(authToken);
                    user.setPasswordHash(_passwordEncoder.encode(changePasswordDto.getNewPassword()));
                    _userRepository.save(user);
                },
                () -> {
                    throw new BadRequestException("Kullanıcı bulunamadı ! " + changePasswordDto.getUserName());
                });
    }

    @Override
    public void ResetPassword(ResetPasswordDto resetPasswordDto) {
        Optional<User> optUser = _userRepository.findByUserName(resetPasswordDto.getUserName());
        optUser.ifPresentOrElse(user -> {
                    user.setPasswordHash(_passwordEncoder.encode(resetPasswordDto.getNewPassword()));
                    _userRepository.save(user);
                },
                () -> {
                    throw new BadRequestException("Kullanıcı bulunamadı ! " + resetPasswordDto.getUserName());
                });
    }
}
