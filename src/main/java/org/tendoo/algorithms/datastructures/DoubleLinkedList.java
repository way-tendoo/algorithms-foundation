package org.tendoo.algorithms.datastructures;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class DoubleLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoubleLinkedList() {
        this.size = 0;
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public int getSize() { return size; }

    public void forEach(Consumer<E> consumer) {
        for (Node<E> node = head; node != null; node = node.next) {
            consumer.accept(node.value);
        }
    }

    public Optional<Node<E>> find(Predicate<E> predicate) {
        for (Node<E> node = head; node != null; node = node.next) {
            if (predicate.test(node.value)) {
                return Optional.of(node);
            }
        }
        return Optional.empty();
    }

    public void pushFront(E value) {
        final Node<E> node = node(value);
        if (isEmpty())
            initEmpty(node);
        else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        size += 1;
    }

    public Optional<E> popFront() {
        if (isEmpty())
            return Optional.empty();
        final Node<E> oldHead = head;
        head = head.next;
        if (head != null)
            head.prev = null;
        else
            tail = null;
        size -= 1;
        return Optional.of(oldHead.value);
    }

    public void pushBack(E value) {
        final Node<E> node = node(value);
        if (isEmpty())
            initEmpty(node);
        else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size += 1;
    }

    public Optional<E> popBack() {
        if (isEmpty())
            return Optional.empty();
        final Node<E> oldTail = tail;
        tail = tail.prev;
        if (tail != null)
            tail.next = null;
        else
            head = null;
        size -= 1;
        return Optional.of(oldTail.value);
    }

    public Node<E> insertAfter(Node<E> node, E value) {
        if (node == tail) {
            pushBack(value);
            return tail;
        }
        final Node<E> newNode = new Node<>(value);
        newNode.next = node.next;
        newNode.prev = node;
        node.next = newNode;
        newNode.next.prev = newNode;
        size += 1;
        return newNode;
    }

    public Node<E> insertBefore(Node<E> node, E value) {
        if (node == head) {
            pushFront(value);
            return head;
        }
        final Node<E> newNode = node(value);
        newNode.next = node;
        newNode.prev = node.prev;
        node.prev = newNode;
        newNode.prev.next = newNode;
        size += 1;
        return newNode;
    }

    public Optional<Node<E>> get(int index) {
        int iter = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (iter == index)
                return Optional.of(node);
            iter++;
        }
        return Optional.empty();
    }

    public Optional<Node<E>> forward(Node<E> start, int current, int forward) {
        int idx = current;
        for (Node<E> node = start; node != null; node = node.next) {
            if (idx == forward)
                return Optional.of(node);
            idx++;
        }
        return Optional.empty();
    }

    public Optional<Node<E>> back(Node<E> start, int current, int back) {
        int idx = current;
        for (Node<E> node = start; node != null; node = node.prev) {
            if (idx == back)
                return Optional.of(node);
            idx--;
        }
        return Optional.empty();
    }

    public E erase(Node<E> node) {
        if (node == head)
            popFront();
        else if (node == tail)
            popBack();
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size -= 1;
        return node.value;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private Node<E> node(E value) {
        return new Node<>(value);
    }

    private void initEmpty(Node<E> node) {
        head = node;
        tail = node;
    }

    public static class Node<E> {
        private E value;
        private Node<E> prev;
        private Node<E> next;

        Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        Node(E value) {
            this.value = value;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
