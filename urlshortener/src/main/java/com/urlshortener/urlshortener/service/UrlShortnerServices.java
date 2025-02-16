package com.urlshortener.urlshortener.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.urlshortener.urlshortener.model.ShortUrl;

@Service
public class UrlShortnerServices {
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

    public String longUrl(String alias) {
        ShortUrl shortUrl = urlStorage.getOrDefault(alias, null);
        if (shortUrl == null) {
            return null;
        }

        return shortUrl.getLongURL();
    }

    public void setAnalytics(String alias) {
        ShortUrl shortUrl = urlStorage.getOrDefault(alias, null);
        if (shortUrl == null) {
            return;
        }

        int accessCount = shortUrl.getAccessCount();
        shortUrl.setAccessCount(accessCount++);

        Instant now = Instant.now();
        shortUrl.addAccessTime(now);
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

        ShortUrl newShortUrl = new ShortUrl();
        newShortUrl.setLongURL(shortUrl.getLongURL());

        if (newAlias == null) {
            newShortUrl.setAlias(alias);
        }

        if (newTTL == null) {
            newTTL = 120;
            newShortUrl.setExpiryTime(Instant.now().plusSeconds(newTTL));
        }

        return baseUrl + "/" + newShortUrl.getAlias();
    }

    private String generateAlias() {
        StringBuilder alias = new StringBuilder();
        for (int i = 0; i < ALIAS_LENGTH; i++) {
            alias.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return alias.toString();
    }

    public boolean deleteAlias(String alias) {
        ShortUrl shortUrl = urlStorage.getOrDefault(alias, null);
        if (shortUrl == null) {
            return false;
        }

        urlStorage.remove(shortUrl.getAlias());
        return true;
    }
}
