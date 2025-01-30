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

    private final String key;
    private final Map<String, Data> value;

    public Node() {
        this.next = null;
        this.prev = null;

        this.key = "";
        this.value = new HashMap<>();
    }

    public Node(String key, Map<String, Data> data) {
        this.next = null;
        this.prev = null;

        this.key = key;
        this.value = data;
    }

    public String getKey() {
        return key;
    }

    public Map<String, Data> getValue() {
        return new HashMap<>(value);
    }

}