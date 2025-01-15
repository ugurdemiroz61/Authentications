package com.uur.Authentications.business;

import com.uur.Authentications.dtos.*;
import com.uur.Authentications.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserDto CreateUser(CreateUserDto createUserDto);

    void UpdateUser(UpdateUserDto updateUserDto);

    UserDto getCurrentUser();

    void ChangePassword(ChangePasswordDto changePasswordDto);

    void ResetPassword(ResetPasswordDto resetPasswordDto);

    Page<UserDto> getUsers(Pageable pageable, UserFilterDto userFilterDto);

    void badCredentials(String userName);

    void successCredentials(String userName);

    Optional<User> findByUserName(String userName);

    Optional<User> findById(long userId);
}
