package com.uur.Authentications.dtos;

import jakarta.validation.constraints.NotNull;

public class CreateUserAuthorityDto {
    @NotNull(message = "Authority id boş bırakılamaz!" )
    private long authorityId;
    @NotNull(message = "User id boş bırakılamaz!" )
    private long userId;

    public CreateUserAuthorityDto() {
    }

    public CreateUserAuthorityDto(long authorityId, long userId) {
        this.authorityId = authorityId;
        this.userId = userId;
    }

    @NotNull(message = "Authority id boş bırakılamaz!")
    public long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(@NotNull(message = "Authority id boş bırakılamaz!") long authorityId) {
        this.authorityId = authorityId;
    }

    @NotNull(message = "User id boş bırakılamaz!")
    public long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User id boş bırakılamaz!") long userId) {
        this.userId = userId;
    }
}
