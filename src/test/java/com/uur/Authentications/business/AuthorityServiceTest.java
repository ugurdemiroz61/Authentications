package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.AuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserAuthorityRepository;
import com.uur.Authentications.dtos.AuthorityDto;
import com.uur.Authentications.dtos.CreateAuthorityDto;
import com.uur.Authentications.entities.Authority;
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

public class AuthorityServiceTest {

    private AuthorityService authorityService;

    private AuthorityRepository _authorityRepository;
    private UserAuthorityRepository _userAuthorityRepository;


    @Before
    public void setUp() throws Exception {
        _authorityRepository = mock(AuthorityRepository.class);
        _userAuthorityRepository = mock(UserAuthorityRepository.class);
        ModelMapper _modelMapper = new ModelMapper();
        authorityService = new AuthorityService(_authorityRepository, _userAuthorityRepository, _modelMapper);
    }

    @Test
    public void whenAddAuthorityCalledWithNotUsedAuthority_itShouldReturnNewAuthorityDto() {
        //Given
        CreateAuthorityDto validCreateAuthorityDto = new CreateAuthorityDto();
        validCreateAuthorityDto.setName("NotUsed");
        when(_authorityRepository.findByName(eq("NotUsed"))).thenReturn(Optional.empty());
        when(_authorityRepository.save(any(Authority.class))).thenAnswer(i -> {
            Authority savedAuthority = (Authority) i.getArguments()[0];
            savedAuthority.setId(1L);
            return savedAuthority;
        });
        //when
        AuthorityDto authorityDto = authorityService.AddAuthority(validCreateAuthorityDto);
        //then
        verify(_authorityRepository).findByName(anyString());
        verify(_authorityRepository).save(any());
        assertNotNull(authorityDto);
    }

    @Test(expected = BadRequestException.class)
    public void whenAddAuthorityCalledWithUsedAuthority_itShouldReturnException() {
        //Given
        CreateAuthorityDto validCreateAuthorityDto = new CreateAuthorityDto();
        validCreateAuthorityDto.setName("Used");
        when(_authorityRepository.findByName(eq("Used"))).thenReturn(Optional.of(new Authority("Used")));
        //when
        authorityService.AddAuthority(validCreateAuthorityDto);
        //then
        verify(_authorityRepository).findByName(anyString());
    }

    @Test
    public void whenRemoveAuthorityCalledWithValidId() {
        //Given
        when(_userAuthorityRepository.existsById(eq(1L))).thenReturn(false);
        when(_authorityRepository.existsById(eq(1L))).thenReturn(true);
        //when
        authorityService.RemoveAuthority(1L);
        //then
        verify(_authorityRepository).deleteById(anyLong());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenRemoveAuthorityCalledWithUnregisteredId_itShouldReturnException() {
        //Given
        when(_userAuthorityRepository.existsById(eq(0L))).thenReturn(false);
        when(_authorityRepository.existsById(eq(0L))).thenReturn(false);
        //when
        authorityService.RemoveAuthority(0L);
        //then

    }

    @Test(expected = BadRequestException.class)
    public void whenRemoveAuthorityCalledWithUsersUsedId_itShouldReturnException() {
        //Given
        when(_userAuthorityRepository.existsById(eq(1L))).thenReturn(true);
        //when
        authorityService.RemoveAuthority(1L);
        //then

    }

    @Test
    public void whenFindAllCalled_itShouldReturnList() {

        Authority authorityAdmin = new Authority("Admin");
        authorityAdmin.setId(1L);
        Authority authorityUser = new Authority("User");
        authorityAdmin.setId(2L);
        List<Authority> Authorities = Arrays.asList(authorityAdmin, authorityUser);
        when(_authorityRepository.findAll()).thenReturn(Authorities);

        List<AuthorityDto> authorityDtos = authorityService.findAll();
        assertNotNull(authorityDtos);
        assertEquals(2, authorityDtos.size());
    }
}