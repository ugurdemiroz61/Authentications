package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.UserAuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.JpaRepositories.UserRoleRepository;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.entities.UserAuthority;
import com.uur.Authentications.entities.UserRole;
import com.uur.Authentications.exceptions.NotFoundException;
import com.uur.Authentications.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository _userRepository;
    private final UserRoleRepository _userRoleRepository;
    private final UserAuthorityRepository _userAuthorityRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, UserAuthorityRepository userAuthorityRepository) {
        _userRepository = userRepository;
        _userRoleRepository = userRoleRepository;
        _userAuthorityRepository = userAuthorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        Optional<User> user = _userRepository.findByUserName(username);
        return user.map(loadUser -> JwtUserDetails.create(loadUser, _userRoleRepository.findByUser(loadUser), _userAuthorityRepository.findByUser(loadUser))).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    public UserDetails loadUserById(long id) {
        User user = _userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        List<UserRole> userRoles = _userRoleRepository.findByUser(user);
        List<UserAuthority> userAuthorities = _userAuthorityRepository.findByUser(user);
        return JwtUserDetails.create(user, userRoles, userAuthorities);
    }

}