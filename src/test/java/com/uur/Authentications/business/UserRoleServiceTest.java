package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.RoleRepository;
import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.JpaRepositories.UserRoleRepository;
import com.uur.Authentications.dtos.CreateUserRoleDto;
import com.uur.Authentications.dtos.UserRoleDto;
import com.uur.Authentications.entities.Role;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.entities.UserRole;
import com.uur.Authentications.exceptions.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserRoleServiceTest {

    private UserRoleService userRoleService;
    private UserRoleRepository _userRoleRepository;
    private RoleRepository _roleRepository;
    private UserRepository _userRepository;

    @Before
    public void setUp() throws Exception {
        _userRoleRepository = mock(UserRoleRepository.class);
        _roleRepository = mock(RoleRepository.class);
        _userRepository = mock(UserRepository.class);
        userRoleService = new UserRoleService(_userRoleRepository, _roleRepository, _userRepository);
    }

    @Test
    public void whenAddUserRoleCalledWidthNotAddedUserRole_itShouldReturnNewUserRoleDto() {
        //Given
        CreateUserRoleDto validCreateUserRoleDto = new CreateUserRoleDto();
        validCreateUserRoleDto.setRoleId(1L);
        validCreateUserRoleDto.setUserId(1L);
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUserName("mock");
        Role mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setName("mock");
        when(_userRepository.findById(eq(1L))).thenReturn(Optional.of(mockUser));
        when(_roleRepository.findById(eq(1L))).thenReturn(Optional.of(mockRole));
        when(_userRoleRepository.save(any(UserRole.class))).thenAnswer(i -> {
            UserRole savedUserRole = (UserRole) i.getArguments()[0];
            savedUserRole.setId(1L);
            return savedUserRole;
        });
        //when
        UserRoleDto roleDto = userRoleService.AddUserRole(validCreateUserRoleDto);
        //then
        verify(_userRepository).findById(any());
        verify(_roleRepository).findById(any());
        verify(_userRoleRepository).save(any());
        assertNotNull(roleDto);
    }

    @Test(expected = NotFoundException.class)
    public void whenAddUserRoleCalledWidthNotRegisterUser_itShouldReturnException() {
        //Given
        CreateUserRoleDto validCreateUserRoleDto = new CreateUserRoleDto();
        validCreateUserRoleDto.setRoleId(1L);
        validCreateUserRoleDto.setUserId(1L);

        Role mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setName("mock");
        when(_userRepository.findById(any())).thenReturn(Optional.empty());
        when(_roleRepository.findById(eq(1L))).thenReturn(Optional.of(mockRole));

        //when
        userRoleService.AddUserRole(validCreateUserRoleDto);
        //then
        verify(_userRepository).findById(any());
    }

    @Test(expected = NotFoundException.class)
    public void whenAddUserRoleCalledWidthNotRegisterRole_itShouldReturnException() {
        //Given
        CreateUserRoleDto validCreateUserRoleDto = new CreateUserRoleDto();
        validCreateUserRoleDto.setRoleId(1L);
        validCreateUserRoleDto.setUserId(1L);

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUserName("mock");

        when(_userRepository.findById(any())).thenReturn(Optional.of(mockUser));
        when(_roleRepository.findById(eq(1L))).thenReturn(Optional.empty());
        //when
        userRoleService.AddUserRole(validCreateUserRoleDto);
        //then
        verify(_roleRepository).findById(any());
    }

    @Test
    public void whenUserRolesCalledWidthRegisteredUserRole_itShouldReturnList() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUserName("mock");

        Role mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setName("");

        UserRole roleAdmin = new UserRole();
        roleAdmin.setId(1L);
        roleAdmin.setRole(mockRole);
        roleAdmin.setUser(mockUser);


        UserRole roleUser = new UserRole();
        roleUser.setId(2L);
        roleUser.setRole(mockRole);
        roleUser.setUser(mockUser);
        List<UserRole> userRoles = Arrays.asList(roleAdmin, roleUser);

        when(_userRepository.findById(any())).thenReturn(Optional.of(mockUser));
        when(_userRoleRepository.findByUser(any())).thenReturn(userRoles);

        List<UserRoleDto> roleDtos = userRoleService.GetUserRoles(1L);
        assertNotNull(roleDtos);
        assertEquals(2, roleDtos.size());
    }

    @Test(expected = NotFoundException.class)
    public void whenUserRolesCalledWidthNotRegistered_itShouldReturnException() {
        when(_userRepository.findById(any())).thenReturn(Optional.empty());
        userRoleService.GetUserRoles(1L);
    }
}