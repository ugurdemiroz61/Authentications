package com.uur.Authentications.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "Authentication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {
    @Column(nullable = false)
    private String name;
}
