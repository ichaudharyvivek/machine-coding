package com.cache;

import java.util.List;

import com.cache.core.Node;
import com.cache.exceptions.NotFoundException;
import com.cache.policy.EvictionPolicy;
import com.cache.storage.Storage;

/**
 * A generic Cache implementation with an eviction policy.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of values stored in this cache
 */
public class Cache<K, V> {
    private int size = 0;
    private final int capacity;
    private final EvictionPolicy<K> evictionPolicy;
    private final Storage<K, V> storage;

    /**
     * Constructs a cache with the given capacity, eviction policy, and storage.
     *
     * @param capacity       the maximum number of items the cache can hold
     * @param evictionPolicy the eviction policy for cache
     * @param storage        the underlying storage implementation
     */
    public Cache(int capacity, EvictionPolicy<K> evictionPolicy, Storage<K, V> storage) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    /**
     * Retrieves a value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key
     * @throws NotFoundException if the key is not found in the cache
     */
    public V get(K key) throws NotFoundException {
        V found = storage.get(key);
        evictionPolicy.access(key);
        return found;
    }

    /**
     * Inserts a key-value pair into the cache. If the cache reaches its capacity,
     * it evicts the least recently used entry before inserting the new item.
     *
     * @param key   the key to insert
     * @param value the value to associate with the key
     * @throws NotFoundException if the eviction process fails due to missing data
     */
    public void put(K key, V value) throws NotFoundException {
        if (size >= capacity) {
            // Capacity reached, evict the least recently used key
            System.out.println(String.format("Capacity of '%d' reached, removing Least Recently Used...", capacity));
            Node<K> removedNode = evictionPolicy.evict();
            storage.delete(removedNode.getKey());
            size--;
        }

        storage.put(key, value);
        evictionPolicy.access(key);
        size++;
    }

    /**
     * Removes the specified key and its associated value from the cache.
     *
     * @param key the key to remove
     * @throws NotFoundException if the key is not found in the cache
     */
    public void delete(K key) throws NotFoundException {
        size--;
        storage.delete(key);
        evictionPolicy.evict(key);
    }

    /**
     * Searches for keys associated with a given value.
     *
     * @param valueMap the value to search for
     * @return a list of keys associated with the specified value
     */
    public List<K> search(V valueMap) {
        return storage.search(valueMap);
    }

    /**
     * Retrieves all keys currently stored in the cache.
     *
     * @return a list of all keys in the cache
     */
    public List<K> keys() {
        return storage.keys();
    }
}
