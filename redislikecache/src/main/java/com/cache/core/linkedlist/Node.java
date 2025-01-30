package com.cache.core.linkedlist;

import java.util.Map;
import java.util.HashMap;

import com.cache.core.datatypes.Data;

import lombok.Getter;
import lombok.Setter;

public class Node {
    @Getter
    @Setter
    private Node next;

    @Getter
    @Setter
    private Node prev;

    @Getter
    private final String key;

    private Map<String, Data> data;

    public Node() {
        this.next = null;
        this.prev = null;

        this.key = "";
        this.data = new HashMap<>();
    }

    public Node(String key, Map<String, Data> data) {
        this.next = null;
        this.prev = null;

        this.key = "";
        this.data = data;
    }
}