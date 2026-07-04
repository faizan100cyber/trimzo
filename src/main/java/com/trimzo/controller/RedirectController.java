package com.trimzo.controller;

import com.trimzo.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final UrlService urlService;

    /**
     * Short URL click → Original URL pe redirect!
     * GET /{shortCode}
     *
     * 302 = Temporary redirect
     * Browser original URL pe chala jayega!
     */
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        // Original URL nikalo
        String originalUrl = urlService
                .getOriginalUrl(shortCode);

        // 302 Redirect response return karo
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}