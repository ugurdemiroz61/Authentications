package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.AuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserAuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.dtos.CreateUserAuthorityDto;
import com.uur.Authentications.dtos.UserAuthorityDto;
import com.uur.Authentications.entities.*;
import com.uur.Authentications.exceptions.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class UserAuthorityServiceTest {

    private UserAuthorityService userAuthorityService;
    private UserAuthorityRepository _userAuthorityRepository;
    private AuthorityRepository _authorityRepository;
    private UserRepository _userRepository;

    @Before
    public void setUp() throws Exception {
        _userAuthorityRepository = mock(UserAuthorityRepository.class);
        _authorityRepository = mock(AuthorityRepository.class);
        _userRepository = mock(UserRepository.class);
        userAuthorityService = new UserAuthorityService(_userAuthorityRepository, _authorityRepository, _userRepository);
    }
    @Test
    public void whenAddUserAuthorityCalledWidthNotAddedUserAuthority_itShouldReturnNewUserAuthorityDto() {
        //Given
        CreateUserAuthorityDto validCreateUserAuthorityDto = new CreateUserAuthorityDto();
        validCreateUserAuthorityDto.setAuthorityId(1L);
        validCreateUserAuthorityDto.setUserId(1L);
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUserName("mock");
        Authority mockAuthority = new Authority();
        mockAuthority.setId(1L);
        mockAuthority.setName("mock");
        when(_userRepository.findById(eq(1L))).thenReturn(Optional.of(mockUser));
        when(_authorityRepository.findById(eq(1L))).thenReturn(Optional.of(mockAuthority));
        when(_userAuthorityRepository.save(any(UserAuthority.class))).thenAnswer(i -> {
            UserAuthority savedUserAuthority = (UserAuthority) i.getArguments()[0];
            savedUserAuthority.setId(1L);
            return savedUserAuthority;
        });
        //when
        UserAuthorityDto authorityDto = userAuthorityService.AddUserAuthority(validCreateUserAuthorityDto);
        //then
        verify(_userRepository).findById(any());
        verify(_authorityRepository).findById(any());
        verify(_userAuthorityRepository).save(any());
        assertNotNull(authorityDto);
    }

    @Test(expected = NotFoundException.class)
    public void whenAddUserAuthorityCalledWidthNotRegisterUser_itShouldReturnException() {
        //Given
        CreateUserAuthorityDto validCreateUserAuthorityDto = new CreateUserAuthorityDto();
        validCreateUserAuthorityDto.setAuthorityId(1L);
        validCreateUserAuthorityDto.setUserId(1L);

        Authority mockAuthority = new Authority();
        mockAuthority.setId(1L);
        mockAuthority.setName("mock");
        when(_userRepository.findById(any())).thenReturn(Optional.empty());
        when(_authorityRepository.findById(eq(1L))).thenReturn(Optional.of(mockAuthority));

        //when
        userAuthorityService.AddUserAuthority(validCreateUserAuthorityDto);
        //then
        verify(_userRepository).findById(any());
    }

    @Test(expected = NotFoundException.class)
    public void whenAddUserAuthorityCalledWidthNotRegisterAuthority_itShouldReturnException() {
        //Given
        CreateUserAuthorityDto validCreateUserAuthorityDto = new CreateUserAuthorityDto();
        validCreateUserAuthorityDto.setAuthorityId(1L);
        validCreateUserAuthorityDto.setUserId(1L);

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUserName("mock");

        when(_userRepository.findById(any())).thenReturn(Optional.of(mockUser));
        when(_authorityRepository.findById(eq(1L))).thenReturn(Optional.empty());
        //when
        userAuthorityService.AddUserAuthority(validCreateUserAuthorityDto);
        //then
        verify(_authorityRepository).findById(any());
    }

    @Test
    public void whenGetUserAuthoritiesCalledWidthRegisteredUserAuthority_itShouldReturnList() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUserName("mock");

        Authority mockAuthority = new Authority();
        mockAuthority.setId(1L);
        mockAuthority.setName("");

        UserAuthority authorityAdmin = new UserAuthority();
        authorityAdmin.setId(1L);
        authorityAdmin.setAuthority(mockAuthority);
        authorityAdmin.setUser(mockUser);


        UserAuthority authorityUser = new UserAuthority();
        authorityUser.setId(2L);
        authorityUser.setAuthority(mockAuthority);
        authorityUser.setUser(mockUser);
        List<UserAuthority> userAuthorities = Arrays.asList(authorityAdmin, authorityUser);

        when(_userRepository.findById(any())).thenReturn(Optional.of(mockUser));
        when(_userAuthorityRepository.findByUser(any())).thenReturn(userAuthorities);

        List<UserAuthorityDto> authorityDtos = userAuthorityService.getUserAuthorities(1L);
        assertNotNull(authorityDtos);
        assertEquals(2, authorityDtos.size());
    }

    @Test(expected = NotFoundException.class)
    public void whenGetUserAuthoritiesCalledWidthNotRegistered_itShouldReturnException() {
        when(_userRepository.findById(any())).thenReturn(Optional.empty());
        userAuthorityService.getUserAuthorities(1L);
    }
}