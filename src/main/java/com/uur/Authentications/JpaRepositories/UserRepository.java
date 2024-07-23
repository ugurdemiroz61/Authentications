package com.uur.Authentications.JpaRepositories;

import com.uur.Authentications.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(  exported = false)
@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
    Optional<User> findByUserName(String userName);
}
