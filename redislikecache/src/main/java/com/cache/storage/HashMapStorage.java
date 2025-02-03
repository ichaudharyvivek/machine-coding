package com.cache.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cache.exceptions.NotFoundException;

public class HashMapStorage<K, V> implements Storage<K, V> {
    private Map<K, V> cache;

    public HashMapStorage() {
        this.cache = new HashMap<>();
    }

    @Override
    public List<K> keys() {
        List<K> listOfKeys = new ArrayList<>();
        cache.forEach((iKey, iValue) -> {
            listOfKeys.add(iKey);
        });

        return listOfKeys;
    }

    @Override
    public V get(K key) throws NotFoundException {
        V found = cache.get(key);
        if (found == null) {
            throw new NotFoundException(String.format("Value for key '%s' doesn't exist.", key));
        }

        return found;
    }

    @Override
    public V erase(K key) throws NotFoundException {
        if (key == null) {
            throw new IllegalArgumentException("Cannot erase as 'key' provided is null.");
        }

        if (!cache.containsKey(key)) {
            throw new NotFoundException(String.format("Key '%s' does not exists.", key));
        }

        return this.get(key);
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot erase as 'key' provided is null.");
        }

        cache.put(key, value);
    }

    @Override
    public List<K> search(V value) {
        if (value == null) {
            throw new IllegalArgumentException("Value not provided.");
        }

        // TODO: Implement search
        return new ArrayList<>();
    }

}