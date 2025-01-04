package com.uur.Authentications.dtos;

public class AuthorityDto {
    private long id;
    private String name;

    public AuthorityDto() {
    }

    public AuthorityDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
