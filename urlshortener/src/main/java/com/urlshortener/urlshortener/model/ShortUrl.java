package com.urlshortener.urlshortener.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ShortUrl {
    private static final int DEFAULT_WINDOW_SIZE = 10;

    private int accessCount;
    private String alias;
    private String longURL;
    private Instant expiryTime;
    private final List<Instant> accessTimes = new ArrayList<>();

    public boolean isExpired() {
        return Instant.now().isAfter(expiryTime);
    }

    public void addAccessTime(Instant instant) {
        int windowLength = accessTimes.size();
        if (windowLength >= DEFAULT_WINDOW_SIZE) {
            accessTimes.remove(0);
        }

        accessTimes.add(instant);
    }

}