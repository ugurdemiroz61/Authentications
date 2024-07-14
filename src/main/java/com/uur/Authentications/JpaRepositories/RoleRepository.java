package com.uur.Authentications.JpaRepositories;

import com.uur.Authentications.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@RepositoryRestResource(  exported = false)
@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
    Boolean existsById(int id);
}
