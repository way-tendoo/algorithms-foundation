package org.tendoo.algorithms.datastructures.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public final class Goblins {

    private static final String PUSH_BACK = "+";
    private static final String PUSH_MIDDLE = "*";
    private static final String POLL = "-";

    private static void balanceQueue(Deque<Integer> leftQueueHalf, Deque<Integer> rightQueueHalf) {
        int size = leftQueueHalf.size() + rightQueueHalf.size();
        int mid = size / 2;
        while (rightQueueHalf.size() > mid) {
            Integer id = rightQueueHalf.pollFirst();
            leftQueueHalf.addLast(id);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int commandsCount = Integer.parseInt(reader.readLine());

        Deque<Integer> leftQueueHalf = new LinkedList<>();
        Deque<Integer> rightQueueHalf = new LinkedList<>();

        for (int i = 0; i < commandsCount; i++) {
            StringTokenizer command = new StringTokenizer(reader.readLine());
            String action = command.nextToken();

            switch (action) {
                case PUSH_BACK: {
                    int goblinId = Integer.parseInt(command.nextToken());
                    if (leftQueueHalf.isEmpty() && rightQueueHalf.isEmpty())
                        leftQueueHalf.addFirst(goblinId);
                    else {
                        rightQueueHalf.addLast(goblinId);
                        balanceQueue(leftQueueHalf, rightQueueHalf);
                    }
                    break;
                }
                case PUSH_MIDDLE: {
                    int goblinId = Integer.parseInt(command.nextToken());
                    rightQueueHalf.addFirst(goblinId);
                    balanceQueue(leftQueueHalf, rightQueueHalf);
                    break;
                }
                case POLL:
                    System.out.println(leftQueueHalf.poll());
                    balanceQueue(leftQueueHalf, rightQueueHalf);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unsupported action: %s", action));
            }
        }
    }
}
