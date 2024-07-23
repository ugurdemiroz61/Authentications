package com.uur.Authentications.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(schema = "Authentication")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    @Column(nullable = false, unique = true)
    String token;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date expiryDate;
}