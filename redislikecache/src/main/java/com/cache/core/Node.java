package com.cache.core;

import lombok.Getter;
import lombok.Setter;

public class Node<K> {
    @Getter
    @Setter
    private Node<K> next;

    @Getter
    @Setter
    private Node<K> prev;

    private final K key;

    public Node() {
        this.next = null;
        this.prev = null;
        this.key = null;
    }

    public Node(K key) {
        this.next = null;
        this.prev = null;

        this.key = key;
    }

    public K getKey() {
        return key;
    }

}