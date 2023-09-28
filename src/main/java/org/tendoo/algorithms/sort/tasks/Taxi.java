package org.tendoo.algorithms.sort.tasks;

import org.tendoo.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public final class Taxi {

    static class OrdinaryComparator implements Comparator<Long> {
        @Override
        public int compare(Long lhs, Long rhs) {
            return lhs.compareTo(rhs);
        }
    }

    static class DescComparator implements Comparator<Long> {
        @Override
        public int compare(Long lhs, Long rhs) {
            return rhs.compareTo(lhs);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int workersCount = Integer.parseInt(reader.readLine());

        List<Long> distances = Utils.readTokens(reader.readLine(), Long::parseLong, workersCount);
        List<Long> rates = Utils.readTokens(reader.readLine(), Long::parseLong, workersCount);

        distances.sort(new DescComparator());
        rates.sort(new OrdinaryComparator());

        long totalCost = 0;
        for (int i = 0; i < workersCount; i++)
            totalCost += distances.get(i) * rates.get(i);

        System.out.println(totalCost);
    }
}
