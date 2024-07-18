package com.uur.Authentications.business;

import com.uur.Authentications.dtos.*;

import java.util.List;

public interface IUserService {
    UserDto CreateUser(CreateUserDto createUserDto);

    void UpdateUser(UpdateUserDto updateUserDto);

    UserDto getCurrentUser();

    void ChangePassword(ChangePasswordDto changePasswordDto);

    void ResetPassword(ResetPasswordDto resetPasswordDto);

    List<UserDto> getUsers(PagingFilterDto<UserDto> pagingFilterDto);
}
