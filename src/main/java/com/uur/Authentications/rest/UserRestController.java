package com.uur.Authentications.rest;

import com.uur.Authentications.business.IUserService;
import com.uur.Authentications.dtos.*;
import com.uur.Authentications.entities.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserRestController {
    private final IUserService _userService;

    public UserRestController(IUserService userService) {
        _userService = userService;
    }

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
    public Slice<User> getUsers(Pageable pageable,@RequestBody UserFilterDto userFilterDto) {
        return _userService.getUsers(pageable,userFilterDto);
    }
}
