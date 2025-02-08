package com.cache.core;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a node in a doubly-linked list structure.
 * 
 * @param <K> The type of key stored in the node
 */
public class Node<K> {
    @Getter
    @Setter
    private Node<K> next;

    @Getter
    @Setter
    private Node<K> prev;

    @Getter
    private final K key;

    /**
     * Constructs an empty node with null key and null references.
     */
    public Node() {
        this.next = null;
        this.prev = null;
        this.key = null;
    }

    /**
     * Constructs a node with the specified key and null references.
     * 
     * @param key The key to store in this node
     */
    public Node(K key) {
        this.next = null;
        this.prev = null;

        this.key = key;
    }

}