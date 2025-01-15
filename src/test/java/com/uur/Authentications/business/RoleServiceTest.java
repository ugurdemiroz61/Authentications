package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.RoleRepository;
import com.uur.Authentications.JpaRepositories.UserRoleRepository;
import com.uur.Authentications.dtos.CreateRoleDto;
import com.uur.Authentications.dtos.RoleDto;
import com.uur.Authentications.entities.Role;
import com.uur.Authentications.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class RoleServiceTest {

    private RoleService roleService;

    private RoleRepository _roleRepository;

    private UserRoleRepository _userRoleRepository;

    @Before
    public void setUp() throws Exception {

        _roleRepository = mock(RoleRepository.class);

        _userRoleRepository = mock(UserRoleRepository.class);

        ModelMapper _modelMapper = new ModelMapper();

        roleService = new RoleService(_roleRepository, _userRoleRepository, _modelMapper);

    }


    @Test
    public void whenAddRoleCalledWithNotUsedRole_itShouldReturnNewRoleDto() {
        //Given
        CreateRoleDto validCreateRoleDto = new CreateRoleDto();
        validCreateRoleDto.setName("NotUsed");
        when(_roleRepository.findByName(eq("NotUsed"))).thenReturn(Optional.empty());
        when(_roleRepository.save(any(Role.class))).thenAnswer(i -> {
            Role savedRole = (Role) i.getArguments()[0];
            savedRole.setId(1L);
            return savedRole;
        });
        //when
        RoleDto roleDto = roleService.AddRole(validCreateRoleDto);
        //then
        verify(_roleRepository).findByName(anyString());
        verify(_roleRepository).save(any());
        assertNotNull(roleDto);
    }

    @Test(expected = BadRequestException.class)
    public void whenAddRoleCalledWithUsedRole_itShouldReturnException() {
        //Given
        CreateRoleDto validCreateRoleDto = new CreateRoleDto();
        validCreateRoleDto.setName("Used");
        when(_roleRepository.findByName(eq("Used"))).thenReturn(Optional.of(new Role("Used")));
        //when
        roleService.AddRole(validCreateRoleDto);
        //then
        verify(_roleRepository).findByName(anyString());
    }

    @Test
    public void whenRemoveRoleCalledWithValidId() {
        //Given
        when(_userRoleRepository.existsById(eq(1L))).thenReturn(false);
        when(_roleRepository.existsById(eq(1L))).thenReturn(true);
        //when
        roleService.RemoveRole(1L);
        //then
        verify(_roleRepository).deleteById(anyLong());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenRemoveRoleCalledWithUnregisteredId_itShouldReturnException() {
        //Given
        when(_userRoleRepository.existsById(eq(0L))).thenReturn(false);
        when(_roleRepository.existsById(eq(0L))).thenReturn(false);
        //when
         roleService.RemoveRole(0L);
        //then

    }

    @Test(expected = BadRequestException.class)
    public void whenRemoveRoleCalledWithUsersUsedId_itShouldReturnException() {
        //Given
        when(_userRoleRepository.existsById(eq(1L))).thenReturn(true);
        //when
        roleService.RemoveRole(1L);
        //then

    }

    @Test
    public void whenFindAllCalled_itShouldReturnList() {

        Role roleAdmin = new Role("Admin");
        roleAdmin.setId(1L);
        Role roleUser = new Role("User");
        roleAdmin.setId(2L);
        List<Role> Roles = Arrays.asList(roleAdmin, roleUser);
        when(_roleRepository.findAll()).thenReturn(Roles);

        List<RoleDto> roleDtos = roleService.findAll();
        assertNotNull(roleDtos);
        assertEquals(2, roleDtos.size());
    }
}