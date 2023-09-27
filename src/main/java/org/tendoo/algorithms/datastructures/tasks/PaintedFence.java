package org.tendoo.algorithms.datastructures.tasks;

import org.tendoo.algorithms.api.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public final class PaintedFence {

    private static final int NOT_PAINTED_BOARD = 0;

    private static final int PAINTING = 1;
    private static final int GET_BOARD_COLOR = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int actionsCount = Integer.parseInt(reader.readLine());

        Stack<Pair<Integer, Integer>> fence = new Stack<>();

        for (int i = 0; i < actionsCount; i++) {
            StringTokenizer command = new StringTokenizer(reader.readLine());
            int actionId = Integer.parseInt(command.nextToken());

            if (actionId == PAINTING) {
                int boardsCount = Integer.parseInt(command.nextToken());
                int color = Integer.parseInt(command.nextToken());
                while (!fence.isEmpty() && boardsCount > fence.peek().getFirst())
                    fence.pop();
                fence.push(new Pair<>(boardsCount, color));
            } else if (actionId == GET_BOARD_COLOR) {
                int boardId = Integer.parseInt(command.nextToken());
                int left = 0;
                int right = fence.size() - 1;

                if (boardId > fence.elementAt(0).getFirst()) {
                    System.out.println(NOT_PAINTED_BOARD);
                } else {
                    while (left <= right) {
                        int mid = left + (right - left) / 2;
                        if (fence.elementAt(mid).getFirst() < boardId) {
                            right = mid - 1;
                        } else {
                            left = mid + 1;
                        }
                    }
                    System.out.println(fence.elementAt(right).getSecond());
                }
            } else {
                throw new IllegalArgumentException(String.format("Unsupported action: %s", actionId));
            }
        }
    }
}
