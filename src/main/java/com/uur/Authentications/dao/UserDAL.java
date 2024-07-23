package com.uur.Authentications.dao;

import com.uur.Authentications.entities.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAL {
    private EntityManager entityManager;

    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    public Optional<User> findById(int Id) {
        return Optional.ofNullable(entityManager.find(User.class, Id));
    }
   /* public User findByUserName(){
         entityManager
    }*/
}
