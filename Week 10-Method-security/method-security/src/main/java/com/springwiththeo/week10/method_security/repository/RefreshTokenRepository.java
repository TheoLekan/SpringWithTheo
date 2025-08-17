package com.springwiththeo.week10.method_security.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findAllByUser(User user);

    void deleteByToken(String token);

    void deleteAllByUser(User user);

    @Transactional
    @Modifying
    int deleteByExpiryAtBefore(java.time.Instant expiryDate);
}
