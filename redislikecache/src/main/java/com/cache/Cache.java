package com.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cache.exceptions.NotFoundException;
import com.cache.policy.EvictionPolicy;
import com.cache.storage.Storage;

public class Cache<K, V> {
    private int size = 0;
    private final int capacity;
    private final EvictionPolicy<K> evictionPolicy;
    private final Storage<K, V> storage;

    public Cache(int capacity, EvictionPolicy<K> evictionPolicy, Storage<K, V> storage) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public V get(K key) throws NotFoundException {
        V found = storage.get(key);
        evictionPolicy.access(key);
        return found;
    }

    public void put(K key, V value) {
        if (size >= capacity) {
            // Capacity reached, evict the least recently used key
            System.out.println(String.format("Capacity of '%d' reached, removing Least Recently Used...", capacity));
            evictionPolicy.evict();
            size--;
        }

        storage.put(key, value);
        evictionPolicy.access(key);
        size++;
    }

    public void delete(K key) throws NotFoundException {
        storage.delete(key);
        evictionPolicy.evict(key);
    }

    public List<K> search(V valueMap) {
        return storage.search(valueMap);
    }
}
