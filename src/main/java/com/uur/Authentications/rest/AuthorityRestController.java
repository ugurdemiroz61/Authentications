package com.uur.Authentications.rest;

import com.uur.Authentications.business.IAuthorityService;
import com.uur.Authentications.dtos.AuthorityDto;
import com.uur.Authentications.dtos.CreateAuthorityDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("authorities")

public class AuthorityRestController {
    private final IAuthorityService _IAuthorityService;

    public AuthorityRestController(IAuthorityService iAuthorityService) {
        _IAuthorityService = iAuthorityService;
    }

    @GetMapping()
    public ResponseEntity<List<AuthorityDto>> GetAuthority() {
        return ResponseEntity.status(HttpStatus.OK).body(_IAuthorityService.findAll());
    }


    @PostMapping()
    public ResponseEntity<AuthorityDto> AddAuthority(@RequestBody @Valid CreateAuthorityDto authorityDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_IAuthorityService.AddAuthority(authorityDto));
    }

    @DeleteMapping("{authorityId}")
    public ResponseEntity<Void> RemoveAuthority(@PathVariable long authorityId) {
        _IAuthorityService.RemoveAuthority(authorityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
