package com.api.urlshortener.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.urlshortener.model.ShortUrl;
import com.api.urlshortener.service.UrlShortenerService;

@RestController
@RequestMapping("/")
public class UrlShortenerController {

    private UrlShortenerService urlShortnerServices;

    public UrlShortenerController(UrlShortenerService urlShortnerServices) {
        this.urlShortnerServices = urlShortnerServices;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = Map.of("success", true, "msg", "Rest API working.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{alias}")
    public ResponseEntity<?> getShortenedUrl(@PathVariable String alias) {
        String longUrl = urlShortnerServices.getLongUrl(alias);
        if (longUrl == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("The url with alias %s doesn't exists or is expired.", alias));
        }

        boolean isAnalyticsSet = urlShortnerServices.setAnalytics(alias);
        if (!isAnalyticsSet) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong.");
        }

        return ResponseEntity.status(302).header("Location", longUrl).build();
    }

    @GetMapping("/analytics/{alias}")
    public ResponseEntity<ShortUrl> getAnalytics(@PathVariable String alias) {
        ShortUrl shortUrl = urlShortnerServices.getAnalytics(alias);
        if (shortUrl == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("The url with alias %s doesn't exists or is expired.", alias));
        }

        return ResponseEntity.ok(shortUrl);
    }

    @PostMapping("/shorten")
    public ResponseEntity<Map<String, Object>> shortenUrl(@RequestBody Map<String, Object> request) {
        String longURL = (String) request.getOrDefault("long_url", null);
        String alias = (String) request.getOrDefault("custom_alias", null);
        Integer ttlSeconds = (Integer) request.get("ttl_seconds");

        if (longURL == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Long URL cannot be null. Please provide `long_url`");
        }

        String shortenedUrl = urlShortnerServices.shortUrl(longURL, alias, ttlSeconds);

        Map<String, Object> response = Map.of("shortened_url", shortenedUrl);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{alias}")
    public ResponseEntity<Map<String, Object>> updateShortenedUrl(@PathVariable String alias,
            @RequestBody Map<String, Object> request) {
        String newAlias = (String) request.get("custom_alias");
        Integer newTTL = (Integer) request.get("ttl_seconds");
        if (newAlias == null && newTTL == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Please provide either custom alias or new ttl.");
        }

        String updatedShortUrl = urlShortnerServices.updateShortUrl(alias, newAlias, newTTL);
        if (updatedShortUrl == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alias not found or it is expired.");
        }

        Map<String, Object> response = Map.of("updated_url", updatedShortUrl);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{alias}")
    public ResponseEntity<?> deleteAlias(@PathVariable String alias) {
        boolean isDeleted = urlShortnerServices.deleteAlias(alias);
        if (isDeleted == false) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot delete because the provided alias doesn't exists or is expired");
        }

        return ResponseEntity.ok(Map.of("success", true));
    }
}