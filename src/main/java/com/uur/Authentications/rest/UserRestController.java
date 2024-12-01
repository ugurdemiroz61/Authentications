package com.uur.Authentications.rest;

import com.uur.Authentications.business.IUserService;
import com.uur.Authentications.dtos.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("users")
public class UserRestController {
    private final IUserService _userService;

    public UserRestController(IUserService userService) {
        _userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> CreateUser(@RequestBody @Valid CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_userService.CreateUser(createUserDto));
    }

    @PutMapping()
    public ResponseEntity<Void> UpdateUser(@RequestBody @Valid UpdateUserDto updateUserDto) {
        _userService.UpdateUser(updateUserDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("getCurrentUser")
    public ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.status(HttpStatus.OK).body(_userService.getCurrentUser());
    }

    @PutMapping("ChangePassword")
    public ResponseEntity<Void> ChangePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto) {
        _userService.ChangePassword(changePasswordDto);
        log.debug("Password changed");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("ResetPassword")
    public ResponseEntity<Void> ResetPassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        _userService.ResetPassword(resetPasswordDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping()
    public ResponseEntity<Page<UserDto>> getUsers(@RequestBody Pageable pageable, @RequestBody UserFilterDto userFilterDto) {
        return ResponseEntity.status(HttpStatus.OK).body(_userService.getUsers(pageable, userFilterDto));
    }
}