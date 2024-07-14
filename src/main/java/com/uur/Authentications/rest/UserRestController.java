package com.uur.Authentications.rest;

import com.uur.Authentications.business.IUserService;
import com.uur.Authentications.dtos.*;
import com.uur.Authentications.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService _userService;



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto CreateUser(@RequestBody @Valid CreateUserDto createUserDto) {
        return _userService.CreateUser(createUserDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateUser(@RequestBody @Valid UpdateUserDto updateUserDto) {
        _userService.UpdateUser(updateUserDto);
    }

    @GetMapping("getCurrentUser")
    public UserDto getCurrentUser() {
        return _userService.getCurrentUser();
    }

    @PutMapping("ChangePassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ChangePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto) {
        _userService.ChangePassword(changePasswordDto);
    }

    @PutMapping("ResetPassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ResetPassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        _userService.ResetPassword(resetPasswordDto);
    }

    @GetMapping()
    public List<UserDto> getUsers(@RequestBody PagingFilterDto<UserDto> pagingFilterDto) {
        return _userService.getUsers(pagingFilterDto);
    }
}
