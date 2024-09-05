package com.uur.Authentications.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "Authentication")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole  extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;
}