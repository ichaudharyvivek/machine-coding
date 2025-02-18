package com.api.urlshortener.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanupTask {
    private final UrlShortenerService urlShortenerService;

    public CleanupTask(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    /**
     * Runs every 60 seconds
     */
    @Scheduled(fixedRate = 60000)
    public void cleanup() {
        urlShortenerService.cleanupExpiredUrls();
    }
}