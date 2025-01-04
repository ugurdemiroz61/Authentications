package com.uur.Authentications.dtos;


public class UserAuthorityDto {
    private long id;
    private long authorityId;
    private String authorityName;
    private long userId;

    public UserAuthorityDto(long id, long authorityId, String authorityName, long userId) {
        this.id = id;
        this.authorityId = authorityId;
        this.authorityName = authorityName;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public long getAuthorityId() {
        return authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public long getUserId() {
        return userId;
    }
}
