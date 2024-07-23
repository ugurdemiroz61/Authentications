package com.uur.Authentications.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "Authentication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends BaseEntity {
    @Column(nullable = false)
    private String name;

}
