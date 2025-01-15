package com.uur.Authentications.business;

import com.uur.Authentications.JpaRepositories.UserAuthorityRepository;
import com.uur.Authentications.JpaRepositories.UserRepository;
import com.uur.Authentications.JpaRepositories.UserRoleRepository;
import com.uur.Authentications.dtos.*;
import com.uur.Authentications.entities.*;
import com.uur.Authentications.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserAuthorityRepository userAuthorityRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setUserName("user");
        createUserDto.setEmail("user@example.com");
        createUserDto.setPassword("password");
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.existsByUserName("user")).thenReturn(false);
        when(userRepository.existsByEmail("user@example.com")).thenReturn(false);
        when(modelMapper.map(createUserDto, User.class)).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.CreateUser(createUserDto);

        assertNotNull(result);
        verify(userRepository).save(user);
    }

    @Test
    void testCreateUser_UsernameExists() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setUserName("user");
        createUserDto.setEmail("user@example.com");
        createUserDto.setPassword("password");

        when(userRepository.existsByUserName("user")).thenReturn(true);

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> userService.CreateUser(createUserDto));
        assertEquals("Kullanıcı adı daha önce kullanılmış!", thrown.getMessage());
    }

    @Test
    void testUpdateUser_Success() {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setId(1L);
        updateUserDto.setName("Updated Name");
        updateUserDto.setSurname("Updated Surname");
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        userService.UpdateUser(updateUserDto);

        verify(userRepository).save(user);
    }

    @Test
    void testUpdateUser_UserNotFound() {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setId(1L);
        updateUserDto.setName("Updated Name");
        updateUserDto.setSurname("Updated Surname");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> userService.UpdateUser(updateUserDto));
        assertEquals("Güncellenecek kullanıcı id bulunamadı! 1", thrown.getMessage());
    }

    @Test
    void testGetCurrentUser_Success() {
        String userName = "user";
        User user = new User();
        UserDto userDto = new UserDto();
        List<RoleDto> roles = List.of(new RoleDto(1L, "ROLE_USER"));
        List<AuthorityDto> authorities = List.of(new AuthorityDto(1L, "AUTH_READ"));

        UserDetails userDetails = mock(UserDetails.class);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(userName);
        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(user));
        when(userRoleRepository.findByUser(user)).thenReturn(List.of());
        when(userAuthorityRepository.findByUser(user)).thenReturn(List.of());
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        Role role = new Role();
        role.setName("ROLE_USER");
        role.setId(1L);
        userRole.setRole(role);
        when(userRoleRepository.findByUser(user)).thenReturn(List.of(userRole));

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUser(user);
        Authority authority = new Authority();
        authority.setId(1L);
        authority.setName("AUTH_READ");
        userAuthority.setAuthority(authority);
        when(userAuthorityRepository.findByUser(user)).thenReturn(List.of(userAuthority));

        UserDto result = userService.getCurrentUser();

        assertNotNull(result);
        verify(userRepository).findByUserName(userName);
    }

    @Test
    void testChangePassword_Success() {
        ChangePasswordDto changePasswordDto = new ChangePasswordDto("user", "currentPassword", "newPassword");
        User user = new User();
        user.setUserName("user");

        UserDetails userDetails = mock(UserDetails.class);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user");
        when(userRepository.findByUserName("user")).thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(userRepository.save(user)).thenReturn(user);

        userService.ChangePassword(changePasswordDto);

        verify(userRepository).save(user);
    }

    @Test
    void testChangePassword_UserNotFound() {
        ChangePasswordDto changePasswordDto = new ChangePasswordDto("user", "currentPassword", "newPassword");

        UserDetails userDetails = mock(UserDetails.class);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user");
        when(userRepository.findByUserName("user")).thenReturn(Optional.empty());

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> userService.ChangePassword(changePasswordDto));
        assertEquals("Kullanıcı bulunamadı ! user", thrown.getMessage());
    }

    @Test
    void testResetPassword_Success() {
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto("user", "newPassword");
        User user = new User();
        user.setUserName("user");

        when(userRepository.findByUserName("user")).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(userRepository.save(user)).thenReturn(user);

        userService.ResetPassword(resetPasswordDto);

        verify(userRepository).save(user);
    }

    @Test
    void testResetPassword_UserNotFound() {
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto("user", "newPassword");

        when(userRepository.findByUserName("user")).thenReturn(Optional.empty());

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> userService.ResetPassword(resetPasswordDto));
        assertEquals("Kullanıcı bulunamadı ! user", thrown.getMessage());
    }
}