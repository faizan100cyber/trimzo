package com.trimzo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UrlResponse {

    private Long id;
    private String shortCode;

    // Poora clickable URL
    // e.g. http://localhost:8080/aB3xYz
    private String shortUrl;

    private String originalUrl;
    private String title;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}