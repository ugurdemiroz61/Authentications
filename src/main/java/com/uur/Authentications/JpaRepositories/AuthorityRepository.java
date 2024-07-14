package com.uur.Authentications.JpaRepositories;

import com.uur.Authentications.entities.Authority;
import com.uur.Authentications.entities.RefreshToken;
import com.uur.Authentications.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(  exported = false)
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Optional<Authority> findByName(String name);
}
