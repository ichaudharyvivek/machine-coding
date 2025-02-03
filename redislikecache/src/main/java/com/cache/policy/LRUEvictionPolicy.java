package com.cache.policy;

import java.util.HashMap;
import java.util.Map;

import com.cache.core.DoubleLinkedList;
import com.cache.core.Node;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private DoubleLinkedList<K> dll;
    private Map<K, Node<K>> lookup;

    public LRUEvictionPolicy() {
        this.dll = new DoubleLinkedList<K>();
        this.lookup = new HashMap<>();
    }

    @Override
    public void access(K key) {
        if (lookup.containsKey(key)) {
            // Key exists - rearrange the nodes
            Node<K> node = lookup.get(key);
            dll.remove(node);
            dll.insert(node);

        } else {
            // Key does not exists - add the node
            Node<K> newNode = new Node<>(key);
            dll.insert(newNode);
            lookup.put(key, newNode);
        }
    }

    @Override
    public Node<K> evict() {
        Node<K> removed = dll.removeLast();
        lookup.remove(removed.getKey());
        return removed;
    }

    @Override
    public Node<K> evict(K key) {
        Node<K> toRemoveNode = lookup.get(key);
        dll.remove(toRemoveNode);
        lookup.remove(key);
        return toRemoveNode;
    }
    
}