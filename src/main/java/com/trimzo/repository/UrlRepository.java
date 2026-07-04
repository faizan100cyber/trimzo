package com.trimzo.repository;

import com.trimzo.entity.Url;
import com.trimzo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    // Short code se URL dhundo — Redirect ke liye
    Optional<Url> findByShortCode(String shortCode);

    // User ki saari URLs — Dashboard ke liye
    List<Url> findByUserOrderByCreatedAtDesc(User user);

    // Duplicate check — Same user ne same URL add ki?
    Optional<Url> findByOriginalUrlAndUser(
            String originalUrl, User user);

    // Short code exist karta hai?
    boolean existsByShortCode(String shortCode);
}