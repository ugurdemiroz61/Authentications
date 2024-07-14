package com.uur.Authentications.JpaRepositories;

import com.uur.Authentications.entities.Role;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Boolean existsById(int id);

    List<UserRole> findByUser(User user);

    boolean existsByUserAndRole(User addingUser, Role addingRole);

}
