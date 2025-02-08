package com.cache.policy;

import com.cache.core.Node;

/**
 * Defines the contract for cache eviction policies.
 *
 * @param <K> The type of keys used in the cache
 */
public interface EvictionPolicy<K> {

    /**
     * Records an access to the specified key.
     *
     * @param Key The key being accessed
     */
    public void access(K Key);

    /**
     * Selects and returns the next entry to be evicted based on the policy.
     *
     * @return The node containing the evicted key
     */
    public Node<K> evict();

    /**
     * Evicts a specific key from the policy's tracking.
     *
     * @param key The key to be evicted
     * @return The node containing the evicted key
     */
    public Node<K> evict(K key);
}