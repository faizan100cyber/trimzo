package com.trimzo.controller;

import com.trimzo.dto.request.CreateUrlRequest;
import com.trimzo.dto.response.UrlResponse;
import com.trimzo.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
@Tag(name = "URLs", description = "URL Shortening APIs")
public class UrlController {

    private final UrlService urlService;

    /**
     * Long URL ko shorten karo
     * POST /api/v1/urls
     */
    @Operation(summary = "Shorten a URL")
    @PostMapping
    public ResponseEntity<UrlResponse> shortenUrl(
            @Valid @RequestBody CreateUrlRequest request) {

        UrlResponse response = urlService.shortenUrl(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * User ki saari URLs dekho
     * GET /api/v1/urls
     */
    @Operation(summary = "Get all URLs of logged in user")
    @GetMapping
    public ResponseEntity<List<UrlResponse>> getAllUrls() {

        return ResponseEntity.ok(urlService.getAllUrls());
    }

    /**
     * URL delete karo
     * DELETE /api/v1/urls/{shortCode}
     */
    @Operation(summary = "Delete a URL")
    @DeleteMapping("/{shortCode}")
    public ResponseEntity<String> deleteUrl(
            @PathVariable String shortCode) {

        urlService.deleteUrl(shortCode);
        return ResponseEntity.ok("URL deleted successfully");
    }
}