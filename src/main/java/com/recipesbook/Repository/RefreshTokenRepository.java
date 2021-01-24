package com.recipesbook.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipesbook.Domain.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}