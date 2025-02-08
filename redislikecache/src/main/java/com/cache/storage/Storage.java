package com.cache.storage;

import java.util.List;

import com.cache.exceptions.NotFoundException;

/**
 * Defines the contract for storage policies.
 *
 * @param <K> the type of keys maintained by this storage
 * @param <V> the type of values stored in this storage
 */
public interface Storage<K, V> {

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the given key
     * @throws NotFoundException if the key does not exist in the storage
     */
    public V get(K key) throws NotFoundException;

    /**
     * Stores a key-value pair in the storage. If the key already exists,
     * its value is updated.
     *
     * @param key   the key to store
     * @param value the value associated with the key
     */
    public void put(K key, V value);

    /**
     * Removes the key-value pair associated with the given key.
     *
     * @param key the key to remove
     * @throws NotFoundException if the key does not exist in the storage
     */
    public void delete(K key) throws NotFoundException;

    /**
     * Returns a list of all keys currently stored.
     *
     * @return a list of all keys in the storage
     */
    public List<K> keys();

    /**
     * Searches for all keys associated with the given value.
     *
     * @param value the value to search for
     * @return a list of keys that have the specified value
     */
    public List<K> search(V value);
}