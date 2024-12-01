package com.uur.Authentications.rest;

import com.uur.Authentications.business.IRoleService;
import com.uur.Authentications.dtos.CreateRoleDto;
import com.uur.Authentications.dtos.RoleDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("roles")
public class RoleRestController {
    private final IRoleService _roleService;

    public RoleRestController(IRoleService roleService) {
        _roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<RoleDto>> GetRole() {
        return ResponseEntity.status(HttpStatus.OK).body(_roleService.findAll());
    }


    @PostMapping()
    public ResponseEntity<RoleDto> AddRole(@RequestBody @Valid CreateRoleDto roleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_roleService.AddRole(roleDto));
    }

    @DeleteMapping("{roleId}")
    public ResponseEntity<Void> RemoveRole(@PathVariable long roleId) {
        _roleService.RemoveRole(roleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
