package com.uur.Authentications.rest;

import com.uur.Authentications.business.IUserAuthorityService;
import com.uur.Authentications.dtos.CreateUserAuthorityDto;
import com.uur.Authentications.dtos.UserAuthorityDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userAuthorities")
@RequiredArgsConstructor
public class UserAuthorityRestController {
    private final IUserAuthorityService _userAuthorityService;

    @GetMapping("{userId}")
    public List<UserAuthorityDto> GetUserAuthorities(@PathVariable int userId) {
        return _userAuthorityService.getUserAuthorities(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserAuthorityDto AddUserAuthority(@RequestBody @Valid CreateUserAuthorityDto createUserAuthorityDto) {
        return _userAuthorityService.AddUserAuthority(createUserAuthorityDto);
    }

    @DeleteMapping("{userAuthorityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void RemoveUserAuthority(@PathVariable int userAuthorityId) {
        _userAuthorityService.RemoveUserAuthority(userAuthorityId);
    }
}
