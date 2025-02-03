package com.cache.policy;

public interface EvictionPolicy<K> {
    public void access(K key);

    public K evict();

    public K evict(K key);
}