package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.RoleRepository;
import com.uur.Authentications.JpaRepositories.UserRoleRepository;
import com.uur.Authentications.dtos.CreateRoleDto;
import com.uur.Authentications.dtos.RoleDto;
import com.uur.Authentications.entities.Role;
import com.uur.Authentications.exeptions.BadRequestException;
import com.uur.Authentications.exeptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService implements IRoleService {
    private final RoleRepository _roleRepository;
    private final UserRoleRepository _userRoleRepository;
    private final ModelMapper _modelMapper;

    @Override
    public RoleDto AddRole(CreateRoleDto createRoleDto) {
        Optional<Role> roleOptional = _roleRepository.findByName(createRoleDto.getName());
        Role addingRole = _modelMapper.map(createRoleDto, Role.class);
        roleOptional.ifPresentOrElse(
                ExistingRole -> {
                    throw new BadRequestException("Role adı daha önce kullanıldı tekrar eklenemez! " + ExistingRole.getId());
                },
                () -> {
                    _roleRepository.save(addingRole);
                });
        return _modelMapper.map(addingRole, RoleDto.class);
    }

    @Override
    public void RemoveRole(int roleId) {
        if (!_userRoleRepository.existsById(roleId)) {
            if (_roleRepository.existsById(roleId)) {
                _roleRepository.deleteById(roleId);
            } else {
                throw new NotFoundException("roleId:" + roleId + " bulunamadı");
            }
        } else {
            throw new BadRequestException("Silmek istediğiniz rol diğer kullanıcılara tanımlıdır silinemez!");
        }
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = _roleRepository.findAll();
        return roles
                .stream()
                .map(user -> _modelMapper.map(user, RoleDto.class))
                .toList();
    }
}
