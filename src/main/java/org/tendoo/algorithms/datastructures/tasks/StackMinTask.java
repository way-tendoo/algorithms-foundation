package org.tendoo.algorithms.datastructures.tasks;

import org.tendoo.algorithms.datastructures.StackMin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class StackMinTask {

    private static final String PUSH = "push";
    private static final String POP = "pop";
    private static final String MIN = "min";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int commandsCount = Integer.parseInt(reader.readLine());

        StackMin<Integer> stack = new StackMin<>();

        for (int i = 0; i < commandsCount; i++) {
            StringTokenizer command = new StringTokenizer(reader.readLine());
            String action = command.nextToken();
            switch (action) {
                case PUSH:
                    Integer value = Integer.valueOf(command.nextToken());
                    stack.push(value);
                    break;
                case POP:
                    stack.pop();
                    break;
                case MIN:
                    System.out.println(stack.min());
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unsupported action: %s", action));
            }
        }
    }
}
