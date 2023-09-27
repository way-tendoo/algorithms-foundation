package org.tendoo.algorithms.datastructures.tasks;

import org.tendoo.algorithms.datastructures.QueueMin;
import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public final class Booble {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens = new StringTokenizer(reader.readLine());

        int numsCount = Integer.parseInt(tokens.nextToken());
        int roundsCount = Integer.parseInt(tokens.nextToken());

        List<Integer> numsSequence = Utils.readIntTokens(reader.readLine(), numsCount);
        List<Integer> rounds = Utils.readIntTokens(reader.readLine(), roundsCount);

        int maxWinSum = 0;
        for (int round : rounds) {
            QueueMin<Integer> queue = new QueueMin<>();
            for (int i = 0; i < round; i++) {
                queue.push(numsSequence.get(i));
            }
            int currentMaxWin = queue.min();
            for (int i = round; i < numsSequence.size(); i++) {
                queue.pop();
                queue.push(numsSequence.get(i));
                currentMaxWin = Math.max(currentMaxWin, queue.min());
            }
            maxWinSum += currentMaxWin;
        }
        System.out.println(maxWinSum);
    }
}
