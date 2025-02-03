package com.cache.core.linkedlist;

public class DoubleLinkedList<K> {
    private Node<K> first;
    private Node<K> last;

    public DoubleLinkedList() {
        this.first = new Node<K>();
        this.last = new Node<K>();

        this.first.setNext(last);
        this.last.setPrev(first);
    }

    public void insert(Node<K> node) {
        Node<K> q = first.getNext();

        node.setNext(q);
        node.setPrev(first);

        first.setNext(node);
        q.setPrev(node);
    }

    public void remove(Node<K> node) {
        Node<K> p = node.getPrev();
        Node<K> q = node.getNext();

        p.setNext(q);
        q.setPrev(p);
    }

    public Node<K> removeLast() {
        Node<K> removedNode = last.getPrev();
        remove(removedNode);
        return removedNode;
    }

}