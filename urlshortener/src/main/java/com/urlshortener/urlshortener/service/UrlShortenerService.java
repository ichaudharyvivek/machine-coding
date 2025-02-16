package com.urlshortener.urlshortener.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.urlshortener.urlshortener.model.ShortUrl;

@Service
public class UrlShortenerService {
    private final static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrst0123456789";
    private final static int ALIAS_LENGTH = 7;

    private final Random random = new Random();
    private final Map<String, ShortUrl> urlStorage = new HashMap<>();

    @Value("${app.base-url}")
    private String baseUrl;

    public String shortUrl(String longURL, String customAlias, Integer customTTLSeconds) {
        int ttlSeconds = customTTLSeconds != null ? customTTLSeconds : 120;
        String alias = customAlias != null ? customAlias : generateAlias();
        Instant expiryTime = Instant.now().plusSeconds(ttlSeconds);

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setAlias(alias);
        shortUrl.setLongURL(longURL);
        shortUrl.setExpiryTime(expiryTime);

        urlStorage.put(alias, shortUrl);
        return baseUrl + "/" + alias;
    }

    public String getLongUrl(String alias) {
        ShortUrl shortUrl = urlStorage.getOrDefault(alias, null);
        if (shortUrl == null) {
            return null;
        }

        boolean isExpired = shortUrl.isExpired();
        if (isExpired) {
            urlStorage.remove(alias);
            return null;
        }

        return shortUrl.getLongURL();
    }

    public boolean setAnalytics(String alias) {
        ShortUrl shortUrl = urlStorage.getOrDefault(alias, null);
        if (shortUrl == null) {
            return false;
        }

        shortUrl.addAccessTime(Instant.now());
        shortUrl.setAccessCount(shortUrl.getAccessCount() + 1);
        return true;
    }

    public ShortUrl getAnalytics(String alias) {
        ShortUrl shortUrl = urlStorage.getOrDefault(alias, null);
        if (shortUrl == null) {
            return null;
        }

        return shortUrl;
    }

    public String updateShortUrl(String alias, String newAlias, Integer newTTL) {
        ShortUrl shortUrl = urlStorage.getOrDefault(alias, null);
        if (shortUrl == null) {
            return null;
        }

        boolean isExpired = shortUrl.isExpired();
        if (isExpired) {
            urlStorage.remove(alias);
            return null;
        }

        newTTL = newTTL != null ? newTTL : 120;
        newAlias = newAlias != null ? newAlias : alias;

        ShortUrl newShortUrl = new ShortUrl();
        newShortUrl.setAlias(newAlias);
        newShortUrl.setLongURL(shortUrl.getLongURL());
        newShortUrl.setExpiryTime(Instant.now().plusSeconds(newTTL));

        urlStorage.remove(alias);
        urlStorage.put(newAlias, newShortUrl);

        return baseUrl + "/" + newShortUrl.getAlias();
    }

    public boolean deleteAlias(String alias) {
        ShortUrl shortUrl = urlStorage.getOrDefault(alias, null);
        if (shortUrl == null) {
            return false;
        }

        boolean isExpired = shortUrl.isExpired();
        if (isExpired) {
            urlStorage.remove(alias);
            return false;
        }

        urlStorage.remove(shortUrl.getAlias());
        return true;
    }

    public void cleanupExpiredUrls() {
        Instant now = Instant.now();
        System.out.println("Running cleanup at: " + now);

        urlStorage.entrySet().removeIf(item -> {
            boolean expired = item.getValue().isExpired();
            if (expired) {
                System.out.println("Removing: " + item.getKey());
            }

            return expired;
        });
    }

    private String generateAlias() {
        StringBuilder alias = new StringBuilder();
        for (int i = 0; i < ALIAS_LENGTH; i++) {
            alias.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return alias.toString();
    }
}
