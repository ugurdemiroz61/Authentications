package com.uur.Authentications.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "Authentication")
@Getter
@Setter
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Role role;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;
}
