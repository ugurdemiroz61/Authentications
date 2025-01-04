package com.uur.Authentications.entities;

import com.uur.Authentications.eventListeners.AuditEntityEventListener;
import jakarta.persistence.*;

import java.util.Date;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Long updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}