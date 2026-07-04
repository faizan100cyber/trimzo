package com.trimzo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateUrlRequest {

    // Original long URL — required
    @NotBlank(message = "URL is required")
    private String originalUrl;

    // Optional label
    private String title;

    // Optional expiry
    private LocalDateTime expiresAt;
}