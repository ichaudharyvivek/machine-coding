package com.cache.core.linkedlist;

import java.util.Map;

import com.cache.core.datatypes.Data;

public class DoubleLinkedList {
    private Node first;
    private Node last;

    public DoubleLinkedList() {
        this.first = new Node();
        this.last = new Node();

        this.first.setNext(last);
        this.last.setPrev(first);
    }

    public void insert(String key, Map<String, Data<?>> value) {
        Node newNode = new Node(key, value);
        Node q = first.getNext();

        newNode.setNext(q);
        newNode.setPrev(first);
        first.setNext(newNode);
        q.setPrev(newNode);
    }

    public void remove(Node node) {
        Node p = node.getPrev();
        Node q = node.getNext();

        p.setNext(q);
        q.setPrev(p);
    }

    public Node removeLast() {
        Node removedNode = last.getPrev();
        remove(removedNode);
        return removedNode;
    }

}