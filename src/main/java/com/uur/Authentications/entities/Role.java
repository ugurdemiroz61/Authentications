package com.uur.Authentications.entities;

import jakarta.persistence.*;

@Entity
@Table(schema = "Authentication")
public class Role extends BaseEntity {
    @Column(nullable = false)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
