package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.UserAuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.JpaRepositories.UserRoleRepository;
import com.uur.Authentications.entities.*;
import com.uur.Authentications.security.JwtUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserAuthorityRepository userAuthorityRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Arrange
        String username = "testuser";
        User user = new User();
        user.setId(1L);
        user.setUserName(username);

        Role role = new Role();
        role.setName("TEST_ROLE");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        List<UserRole> userRoles = List.of(userRole);

        Authority authority = new Authority();
        authority.setName("TEST_AUTHORITY");

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUser(user);
        userAuthority.setAuthority(authority);
        List<UserAuthority> userAuthorities = List.of(userAuthority);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(userRoleRepository.findByUser(user)).thenReturn(userRoles);
        when(userAuthorityRepository.findByUser(user)).thenReturn(userAuthorities);

        // Act
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        verify(userRepository, times(1)).findByUserName(username);
        verify(userRoleRepository, times(1)).findByUser(user);
        verify(userAuthorityRepository, times(1)).findByUser(user);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonexistentuser";

        when(userRepository.findByUserName(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> userDetailsServiceImpl.loadUserByUsername(username));
        verify(userRepository, times(1)).findByUserName(username);
        verify(userRoleRepository, never()).findByUser(any(User.class));
        verify(userAuthorityRepository, never()).findByUser(any(User.class));
    }

    @Test
    public void testLoadUserById_UserFound() {
        // Arrange
        long userId = 1L;
        User user = new User();
        user.setId(userId);

        Role role = new Role();
        role.setName("TEST_ROLE");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        List<UserRole> userRoles = List.of(userRole);

        Authority authority = new Authority();
        authority.setName("TEST_AUTHORITY");

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUser(user);
        userAuthority.setAuthority(authority);

        List<UserAuthority> userAuthorities = List.of(userAuthority);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRoleRepository.findByUser(user)).thenReturn(userRoles);
        when(userAuthorityRepository.findByUser(user)).thenReturn(userAuthorities);

        // Act
        UserDetails userDetails = userDetailsServiceImpl.loadUserById(userId);

        // Assert
        assertNotNull(userDetails);
        assertEquals(userId, ((JwtUserDetails) userDetails).getId());
        verify(userRepository, times(1)).findById(userId);
        verify(userRoleRepository, times(1)).findByUser(user);
        verify(userAuthorityRepository, times(1)).findByUser(user);
    }

    @Test
    public void testLoadUserById_UserNotFound() {
        // Arrange
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> userDetailsServiceImpl.loadUserById(userId));
        verify(userRepository, times(1)).findById(userId);
        verify(userRoleRepository, never()).findByUser(any(User.class));
        verify(userAuthorityRepository, never()).findByUser(any(User.class));
    }
}