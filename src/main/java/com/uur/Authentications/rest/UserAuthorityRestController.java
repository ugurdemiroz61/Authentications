package com.uur.Authentications.rest;

import com.uur.Authentications.business.IUserAuthorityService;
import com.uur.Authentications.dtos.CreateUserAuthorityDto;
import com.uur.Authentications.dtos.UserAuthorityDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userAuthorities")
public class UserAuthorityRestController {
    private final IUserAuthorityService _userAuthorityService;

    public UserAuthorityRestController(IUserAuthorityService userAuthorityService) {
        _userAuthorityService = userAuthorityService;
    }

    @GetMapping("{userId}")
    public List<UserAuthorityDto> GetUserAuthorities(@PathVariable long userId) {
        return _userAuthorityService.getUserAuthorities(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserAuthorityDto AddUserAuthority(@RequestBody @Valid CreateUserAuthorityDto createUserAuthorityDto) {
        return _userAuthorityService.AddUserAuthority(createUserAuthorityDto);
    }

    @DeleteMapping("{userAuthorityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void RemoveUserAuthority(@PathVariable long userAuthorityId) {
        _userAuthorityService.RemoveUserAuthority(userAuthorityId);
    }
}
