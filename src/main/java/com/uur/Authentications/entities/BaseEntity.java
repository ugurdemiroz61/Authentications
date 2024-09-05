package com.uur.Authentications.entities;

import com.uur.Authentications.eventListeners.AuditEntityEventListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@EntityListeners(AuditEntityEventListener.class)
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private Long createUser;

    @Column(nullable = true)
    private Date createdDate;

    @Column(nullable = true)
    private Long updatedUser;

    @Column(nullable = true)
    private Date updatedDate;
}