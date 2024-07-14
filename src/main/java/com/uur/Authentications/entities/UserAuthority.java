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
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Authority authority;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;
}
