package com.uur.Authentications.dao;

import com.uur.Authentications.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAL {
    private final EntityManager entityManager;

    public UserDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    public Optional<User> findById(int Id) {
        return Optional.ofNullable(entityManager.find(User.class, Id));
    }
}
