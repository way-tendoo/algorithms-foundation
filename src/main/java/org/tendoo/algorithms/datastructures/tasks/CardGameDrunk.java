package org.tendoo.algorithms.datastructures.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public final class CardGameDrunk {

    private static final int MAX_MOVES_COUNT = 200000;

    private static final String DRAW = "draw";

    private static Queue<Integer> readTokensToQueue(String line) {
        StringTokenizer tokens = new StringTokenizer(line);
        Queue<Integer> nums = new LinkedList<>();
        while (tokens.hasMoreTokens()) {
            nums.add(Integer.parseInt(tokens.nextToken()));
        }
        return nums;
    }

    private static boolean isWinningCardCombination(int lhs, int rhs, int cardCount) {
        if (lhs == 0 && rhs == cardCount - 1) return true;
        if (lhs == cardCount - 1 && rhs == 0) return false;
        return lhs > rhs;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int cardCount = Integer.parseInt(reader.readLine());

        Queue<Integer> fstGamerCards = readTokensToQueue(reader.readLine());
        Queue<Integer> sndGamerCards = readTokensToQueue(reader.readLine());

        for (int i = 0; i < MAX_MOVES_COUNT; i++) {
            if (fstGamerCards.isEmpty()) {
                System.out.printf("second %s%n", i);
                return;
            }
            if (sndGamerCards.isEmpty()) {
                System.out.printf("first %s%n", i);
                return;
            }
            int firstCard = fstGamerCards.poll();
            int secondCard = sndGamerCards.poll();

            if (isWinningCardCombination(firstCard, secondCard, cardCount)) {
                fstGamerCards.add(firstCard);
                fstGamerCards.add(secondCard);
            } else  {
                sndGamerCards.add(firstCard);
                sndGamerCards.add(secondCard);
            }
        }
        System.out.println(DRAW);
    }
}
