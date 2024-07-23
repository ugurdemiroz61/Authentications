package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.RoleRepository;
import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.JpaRepositories.UserRoleRepository;
import com.uur.Authentications.dtos.CreateUserRoleDto;
import com.uur.Authentications.dtos.UserRoleDto;
import com.uur.Authentications.entities.Role;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.entities.UserRole;
import com.uur.Authentications.exeptions.BadRequestException;
import com.uur.Authentications.exeptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService implements IUserRoleService {
    private final UserRoleRepository _userRoleRepository;
    private final RoleRepository _roleRepository;
    private final UserRepository _userRepository;


    @Override
    public UserRoleDto AddUserRole(CreateUserRoleDto createUserRoleDto) {
        User addingUser = _userRepository.findById(createUserRoleDto.getUserId()).orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı!"));
        Role addingRole = _roleRepository.findById(createUserRoleDto.getRoleId()).orElseThrow(() -> new NotFoundException("Role bulunamadı!"));
        if (_userRoleRepository.existsByUserAndRole(addingUser, addingRole)) {
            throw new BadRequestException("Role zaten mevcut tekrar eklenemez!");
        } else {
            UserRole userRole = new UserRole();
            userRole.setRole(addingRole);
            userRole.setUser(addingUser);
            userRole = _userRoleRepository.save(userRole);
            return new UserRoleDto(userRole.getId(), userRole.getRole().getId(), userRole.getRole().getName(), userRole.getUser().getId());
        }
    }

    @Override
    public void RemoveUserRole(long userRoleId) {
        Optional<UserRole> userRole = _userRoleRepository.findById(userRoleId);
        userRole.ifPresent(
                _userRoleRepository::delete);
    }

    @Override
    public List<UserRoleDto> GetUserRoles(long userId) {
        User user = _userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı!"));
        return _userRoleRepository.findByUser(user).stream()
                .map(userRole ->
                        new UserRoleDto(userRole.getId(), userRole.getRole().getId(), userRole.getRole().getName(), userRole.getUser().getId())
                )
                .toList();
    }


}
