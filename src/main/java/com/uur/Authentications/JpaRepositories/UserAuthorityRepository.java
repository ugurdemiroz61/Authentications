package com.uur.Authentications.JpaRepositories;

import com.uur.Authentications.entities.Authority;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.entities.UserAuthority;
import com.uur.Authentications.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(  exported = false)
@Repository
public interface UserAuthorityRepository  extends JpaRepository<UserAuthority, Integer> {
    List<UserAuthority> findByUser(User user);

    boolean existsByUserAndAuthority(User addingUser, Authority addingAuthority);
}
