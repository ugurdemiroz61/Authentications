package com.uur.Authentications.rest;

import com.uur.Authentications.business.IUserAuthorityService;
import com.uur.Authentications.dtos.CreateUserAuthorityDto;
import com.uur.Authentications.dtos.UserAuthorityDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("userAuthorities")
public class UserAuthorityRestController {
    private final IUserAuthorityService _userAuthorityService;

    public UserAuthorityRestController(IUserAuthorityService userAuthorityService) {
        _userAuthorityService = userAuthorityService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<UserAuthorityDto>> GetUserAuthorities(@PathVariable long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(_userAuthorityService.getUserAuthorities(userId));
    }

    @PostMapping()
    public ResponseEntity<UserAuthorityDto> AddUserAuthority(@RequestBody @Valid CreateUserAuthorityDto createUserAuthorityDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_userAuthorityService.AddUserAuthority(createUserAuthorityDto));
    }

    @DeleteMapping("{userAuthorityId}")
    public ResponseEntity<Void> RemoveUserAuthority(@PathVariable long userAuthorityId) {
        _userAuthorityService.RemoveUserAuthority(userAuthorityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
