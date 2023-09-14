package org.tendoo.datastructures;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public final class SinglyLinkedList<E> {

    private Node<E> head;
    private int size;

    public SinglyLinkedList() {
        size = 0;
    }

    public void forEach(final Consumer<E> consumer) {
        Node<E> curr = head;
        while (curr != null) {
            consumer.accept(curr.value);
            curr = curr.next;
        }
    }

    public void pushFront(final E value) {
        if (isEmpty())
            head = new Node<>(value);
        else
            head = new Node<>(value, head);
        size += 1;
    }

    public Optional<E> popFront() {
        if (isEmpty())
            return Optional.empty();
        final E value = head.value;
        head = head.next;
        size -= 1;
        return Optional.of(value);
    }

    public Node<E> insertAfter(final Node<E> node, final E value) {
        final Node<E> next = new Node<>(value, node.next);
        node.next = next;
        size += 1;
        return next;
    }

    public E eraseAfter(final Node<E> node) {
        Node<E> erase = node.next;
        node.next = erase.next;
        size -= 1;
        return erase.value;
    }

    public Optional<Node<E>> find(final E value) {
        Node<E> curr = head;
        while (curr != null) {
            if (curr.value == value) {
                return Optional.of(curr);
            }
            curr = curr.next;
        }
        return Optional.empty();
    }

    public Optional<Node<E>> getHead() {
        if (isEmpty())
            return Optional.empty();
        return Optional.of(head);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    static class Node<E> {
        private final E value;
        private Node<E> next;

        Node(final E value) {
            this.value = value;
        }

        Node(final E value, final Node<E> next) {
            this.value = value;
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
