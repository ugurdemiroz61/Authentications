package com.uur.Authentications.entities;

import jakarta.persistence.*;

@Entity
@Table(schema = "Authentication")
public class UserRole  extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    public UserRole() {
    }

    public UserRole(Role role, User user) {
        this.role = role;
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}