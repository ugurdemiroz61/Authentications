package com.uur.Authentications.rest;

import com.uur.Authentications.business.IUserRoleService;
import com.uur.Authentications.dtos.CreateUserRoleDto;
import com.uur.Authentications.dtos.UserRoleDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("userRoles")
public class UserRoleRestController {
    private final IUserRoleService _userRoleService;

    public UserRoleRestController(IUserRoleService userRoleService) {
        _userRoleService = userRoleService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<UserRoleDto>> GetUserRoles(@PathVariable long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(_userRoleService.GetUserRoles(userId));
    }

    @PostMapping()
    public ResponseEntity<UserRoleDto> AddUserRole(@RequestBody @Valid CreateUserRoleDto createUserRoleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_userRoleService.AddUserRole(createUserRoleDto));
    }

    @DeleteMapping("{userRoleId}")
    public ResponseEntity<Void> RemoveUserRole(@PathVariable long userRoleId) {
        _userRoleService.RemoveUserRole(userRoleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
