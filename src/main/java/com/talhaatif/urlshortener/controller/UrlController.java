package com.talhaatif.urlshortener.controller;

import com.talhaatif.urlshortener.model.ShortenedUrl;
import com.talhaatif.urlshortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shorten")
@Tag(
        name = "URL Shortening APIs",
        description = "APIs for shortening, managing, and resolving URLs. Includes endpoints for creating short URLs, retrieving original URLs, and analyzing URL usage statistics."
)
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // Create Short URL
    @PostMapping
    @Operation(summary = "Shorten a URL", description = "Creates a shortened version of the provided URL.")
    public ResponseEntity<ShortenedUrl> shortenUrl(@RequestBody ShortenedUrl request) {
        return ResponseEntity.ok(urlService.createShortUrl(request.getLongUrl()));
    }

    // Retrieve Original URL
    @GetMapping("/{shortCode}")
    @Operation(summary = "Resolve a short URL", description = "Retrieves the original URL from a shortened URL.")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String shortCode) {
        Optional<ShortenedUrl> shortenedUrl = urlService.getOriginalUrl(shortCode);
        return shortenedUrl.map(url -> ResponseEntity.ok(url))
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Short URL
    @PutMapping("/{shortCode}")
    @Operation(summary = "Updates a Long URL", description = "Retrieves the original URL from a shortened URL and updates it with new one provided in Body")
    public ResponseEntity<?> updateUrl(@PathVariable String shortCode, @RequestBody ShortenedUrl request) {
        Optional<ShortenedUrl> updatedUrl = urlService.updateShortUrl(shortCode, request.getLongUrl());
        return updatedUrl.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Delete Short URL
    @Operation(summary = "Deletes a short URL", description = "Delete the short URL if it exists")
    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        return urlService.deleteShortUrl(shortCode) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Get URL Stats
    @Operation(summary = "Retrieves history of a short URL", description = "It provides access count, created time, updated time, id, Long Url of a Short URL given through request parameter")
    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<?> getUrlStats(@PathVariable String shortCode) {
        Optional<ShortenedUrl> stats = urlService.getUrlStats(shortCode);
        return stats.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
