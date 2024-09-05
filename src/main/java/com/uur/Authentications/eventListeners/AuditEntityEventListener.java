package com.uur.Authentications.eventListeners;

import com.uur.Authentications.entities.BaseEntity;
import com.uur.Authentications.security.JwtUserDetails;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class AuditEntityEventListener {
    @PrePersist
    public void preInsert(BaseEntity entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
                entity.setCreateUser(userDetails.getId());
                entity.setCreatedDate(new Date());
            }
        }
    }
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
                entity.setUpdatedUser(userDetails.getId());
                entity.setUpdatedDate(new Date());
            }
        }
    }
}
