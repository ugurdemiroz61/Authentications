package com.uur.Authentications.rest;

import com.uur.Authentications.business.IRoleService;
import com.uur.Authentications.dtos.CreateRoleDto;
import com.uur.Authentications.dtos.RoleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleRestController {
    private  final IRoleService _roleService;

    @GetMapping()
    public List<RoleDto> GetRole(){
        return  _roleService.findAll();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto AddRole(@RequestBody @Valid CreateRoleDto roleDto){
        return _roleService.AddRole(roleDto);
    }

    @DeleteMapping("{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void RemoveRole(@PathVariable long roleId){
        _roleService.RemoveRole(roleId);
    }
}
