package org.tendoo.algorithms.datastructures;

import java.util.Optional;
import java.util.function.Predicate;

public final class Stack<E> {

    private final SinglyLinkedList<E> data;

    public Stack() {
        this.data = new SinglyLinkedList<>();
    }

    public void push(E value) {
        data.pushFront(value);
    }

    public Optional<E> pop() {
        if (isEmpty())
            return Optional.empty();
        return data.popFront();
    }

    public Optional<E> peek() {
        return data.getHead().map(SinglyLinkedList.Node::getValue);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public Optional<E> find(Predicate<E> predicate) {
        return data.find(predicate);
    }
}
