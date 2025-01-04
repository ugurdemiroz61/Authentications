package com.uur.Authentications.entities;
import jakarta.persistence.*;
@Entity
@Table(schema = "Authentication")
public class UserAuthority  extends BaseEntity  {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Authority authority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    public UserAuthority() {
    }

    public UserAuthority(Authority authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}