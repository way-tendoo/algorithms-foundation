package org.tendoo.algorithms.datastructures;

import java.util.Stack;

public final class StackMin<T extends Comparable<T>> {
    private final Stack<T> data;
    private final Stack<T> min;

    public StackMin() {
        this.data = new Stack<>();
        this.min = new Stack<>();
    }

    public void push(T value) {
        data.push(value);
        if (min.isEmpty()) {
            min.push(value);
            return;
        }
        final T minValue = Utils.min(this.min.peek(), value);
        min.push(minValue);
    }

    public T pop() {
        min.pop();
        return data.pop();
    }

    public T min() {
        return min.peek();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
