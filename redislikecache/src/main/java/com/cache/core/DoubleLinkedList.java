package com.cache.core;

/**
 * A doubly-linked list implementation with {@link Node}.
 * 
 * @param <K> The type of key stored in the list nodes
 */
public class DoubleLinkedList<K> {
    private Node<K> first;
    private Node<K> last;

    /**
     * Constructs an empty doubly-linked list with sentinel nodes.
     * Initializes dummy nodes at both ends of the list.
     */
    public DoubleLinkedList() {
        this.first = new Node<K>();
        this.last = new Node<K>();

        this.first.setNext(last);
        this.last.setPrev(first);
    }

    /**
     * Inserts a node at the front of the list.
     * The node is placed between the first sentinel node and the first actual node.
     * 
     * @param node The node to insert
     */
    public void insert(Node<K> node) {
        Node<K> q = first.getNext();

        node.setNext(q);
        node.setPrev(first);

        first.setNext(node);
        q.setPrev(node);
    }

    /**
     * Removes the specified node from the list.
     * Updates the adjacent nodes' references and clears the removed node's
     * references.
     * 
     * @param node The node to remove
     */
    public void remove(Node<K> node) {
        Node<K> p = node.getPrev();
        Node<K> q = node.getNext();

        p.setNext(q);
        q.setPrev(p);

        // Clear the node's links to prevent accidental reuse
        node.setNext(null);
        node.setPrev(null);
    }

    /**
     * Removes and returns the last non-sentinel node from the list.
     * 
     * @return The removed node
     */
    public Node<K> removeLast() {
        Node<K> removedNode = last.getPrev();
        remove(removedNode);
        return removedNode;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list contains no non-sentinel nodes, false otherwise
     */
    public boolean isEmpty() {
        return first.getNext() == last;
    }
}