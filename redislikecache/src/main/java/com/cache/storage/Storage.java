package com.cache.storage;

import java.util.List;

import com.cache.exceptions.NotFoundException;

public interface Storage<K, V> {
    public V get(K key) throws NotFoundException;

    public void put(K key, V value);

    public V erase(K key) throws NotFoundException;

    public List<K> keys();

    public List<K> search(V value);

}