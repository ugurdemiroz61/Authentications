package com.uur.Authentications.JpaRepositories;

import com.uur.Authentications.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@RepositoryRestResource(  exported = false)
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {


    Optional<RefreshToken> findByUserIdAndToken(int userId, String token);

    Optional<RefreshToken> findByUser_Id(int userId);
}