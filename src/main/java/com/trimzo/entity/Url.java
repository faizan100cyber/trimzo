package com.trimzo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
@Getter
@Setter
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Poori lambi URL
    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    // Base62 encoded short code e.g. "aB3xYz"
    @Column(name = "short_code", nullable = false, unique = true)
    private String shortCode;

    // Kaun sa user ne banaya
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Optional label for dashboard
    private String title;

    // Active hai ya disable
    @Column(name = "is_active")
    private Boolean isActive = true;

    // Optional expiry date
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}