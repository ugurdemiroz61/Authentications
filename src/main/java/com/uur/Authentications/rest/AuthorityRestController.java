package com.uur.Authentications.rest;

import com.uur.Authentications.business.IAuthorityService;
import com.uur.Authentications.dtos.AuthorityDto;
import com.uur.Authentications.dtos.CreateAuthorityDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authorities")
@RequiredArgsConstructor
public class AuthorityRestController {
    private  final IAuthorityService _IAuthorityService;

    @GetMapping()
    public List<AuthorityDto> GetAuthority(){
        return  _IAuthorityService.findAll();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorityDto AddAuthority(@RequestBody @Valid CreateAuthorityDto authorityDto){
        return _IAuthorityService.AddAuthority(authorityDto);
    }

    @DeleteMapping("{authorityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void RemoveAuthority(@PathVariable long authorityId){
        _IAuthorityService.RemoveAuthority(authorityId);
    }
}
