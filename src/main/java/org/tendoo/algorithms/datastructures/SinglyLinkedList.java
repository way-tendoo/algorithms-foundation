package org.tendoo.algorithms.datastructures;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class SinglyLinkedList<E> {
    private Node<E> head;
    private int size;

    public SinglyLinkedList() {
        size = 0;
    }

    public void forEach(Consumer<E> consumer) {
        for (Node<E> node = head; node != null; node = node.next) {
            consumer.accept(node.value);
        }
    }

    public void pushFront(E value) {
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

    public Node<E> insertAfter(Node<E> node, E value) {
        final Node<E> next = new Node<>(value, node.next);
        node.next = next;
        size += 1;
        return next;
    }

    public E eraseAfter(Node<E> node) {
        final Node<E> erase = node.next;
        node.next = erase.next;
        size -= 1;
        return erase.value;
    }

    public Optional<Node<E>> find(E value) {
        for (Node<E> node = head; node != null; node = node.next) {
            if (node.value == value) {
                return Optional.of(node);
            }
        }
        return Optional.empty();
    }

    public Optional<E> find(Predicate<E> predicate) {
        for (Node<E> node = head; node != null; node = node.next) {
            if (predicate.test(node.value)) {
                return Optional.of(node.value);
            }
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

        Node(E value) {
            this.value = value;
        }

        Node(E value, Node<E> next) {
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
