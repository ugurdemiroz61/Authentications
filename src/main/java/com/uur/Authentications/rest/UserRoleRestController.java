package com.uur.Authentications.rest;

import com.uur.Authentications.business.IUserRoleService;
import com.uur.Authentications.dtos.CreateUserRoleDto;
import com.uur.Authentications.dtos.UserRoleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userRoles")
@RequiredArgsConstructor
public class UserRoleRestController {
    private final IUserRoleService _userRoleService;

    @GetMapping("{userId}")
    public List<UserRoleDto> GetUserRoles(@PathVariable int userId) {
        return _userRoleService.GetUserRoles(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserRoleDto AddUserRole(@RequestBody @Valid CreateUserRoleDto createUserRoleDto) {
        return _userRoleService.AddUserRole(createUserRoleDto);
    }

    @DeleteMapping("{userRoleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void RemoveUserRole(@PathVariable int userRoleId) {
        _userRoleService.RemoveUserRole(userRoleId);
    }
}
