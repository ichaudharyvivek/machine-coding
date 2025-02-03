package com.cache.policy;

import com.cache.core.Node;

public interface EvictionPolicy<K> {
    public void access(K Key);

    public Node<K> evict();

    public Node<K> evict(K key);
}