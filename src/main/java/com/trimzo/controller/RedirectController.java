package com.trimzo.controller;

import com.trimzo.service.ClickTrackingService;
import com.trimzo.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final ClickTrackingService clickTrackingService;

    /**
     * Redirects short URL to original URL.
     * Extracts request data BEFORE async call —
     * because request object gets recycled after response.
     */
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode,
            HttpServletRequest request) {

        // Get original URL
        String originalUrl =
                urlService.getOriginalUrl(shortCode);

        // Extract all data from request BEFORE async call!
        String ipAddress = extractIp(request);
        String userAgent = request.getHeader("User-Agent");
        String referrer  = request.getHeader("Referer");

        // Pass extracted strings to async method
        // NOT the request object!
        clickTrackingService.trackClick(
                shortCode, ipAddress, userAgent, referrer);

        // Redirect to original URL
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

    /**
     * Extracts real IP address.
     * Handles proxy and load balancer headers.
     */
    private String extractIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() &&
                !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}