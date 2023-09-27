package org.tendoo.algorithms.datastructures;

import org.tendoo.algorithms.Utils;

public final class QueueMin<T extends Comparable<T>> {
    private final StackMin<T> enqueue;
    private final StackMin<T> dequeue;

    public QueueMin() {
        this.enqueue = new StackMin<>();
        this.dequeue = new StackMin<>();
    }

    public void push(T value) {
        enqueue.push(value);
    }

    public T pop() {
        if (dequeue.isEmpty()) {
            while (!enqueue.isEmpty()) {
                dequeue.push(enqueue.pop());
            }
        }
        return dequeue.pop();
    }

    public boolean isEmpty() {
        return enqueue.isEmpty() && dequeue.isEmpty();
    }

    public T min() {
        if (dequeue.isEmpty())
            return enqueue.min();
        if (enqueue.isEmpty())
            return dequeue.min();
        return Utils.min(enqueue.min(), dequeue.min());
    }
}
